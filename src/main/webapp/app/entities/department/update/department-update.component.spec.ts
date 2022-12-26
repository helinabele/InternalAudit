import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DepartmentFormService } from './department-form.service';
import { DepartmentService } from '../service/department.service';
import { IDepartment } from '../department.model';
import { IUserprofile } from 'app/entities/userprofile/userprofile.model';
import { UserprofileService } from 'app/entities/userprofile/service/userprofile.service';
import { IBranch } from 'app/entities/branch/branch.model';
import { BranchService } from 'app/entities/branch/service/branch.service';

import { DepartmentUpdateComponent } from './department-update.component';

describe('Department Management Update Component', () => {
  let comp: DepartmentUpdateComponent;
  let fixture: ComponentFixture<DepartmentUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let departmentFormService: DepartmentFormService;
  let departmentService: DepartmentService;
  let userprofileService: UserprofileService;
  let branchService: BranchService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [DepartmentUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(DepartmentUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DepartmentUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    departmentFormService = TestBed.inject(DepartmentFormService);
    departmentService = TestBed.inject(DepartmentService);
    userprofileService = TestBed.inject(UserprofileService);
    branchService = TestBed.inject(BranchService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Userprofile query and add missing value', () => {
      const department: IDepartment = { id: 'CBA' };
      const userproifle: IUserprofile = { id: '1c3c7556-dfd1-4896-b650-4227501ad31c' };
      department.userproifle = userproifle;

      const userprofileCollection: IUserprofile[] = [{ id: '973ddef2-1b4d-4a48-b5e1-5808bf41eaf1' }];
      jest.spyOn(userprofileService, 'query').mockReturnValue(of(new HttpResponse({ body: userprofileCollection })));
      const additionalUserprofiles = [userproifle];
      const expectedCollection: IUserprofile[] = [...additionalUserprofiles, ...userprofileCollection];
      jest.spyOn(userprofileService, 'addUserprofileToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ department });
      comp.ngOnInit();

      expect(userprofileService.query).toHaveBeenCalled();
      expect(userprofileService.addUserprofileToCollectionIfMissing).toHaveBeenCalledWith(
        userprofileCollection,
        ...additionalUserprofiles.map(expect.objectContaining)
      );
      expect(comp.userprofilesSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Branch query and add missing value', () => {
      const department: IDepartment = { id: 'CBA' };
      const branch: IBranch = { id: '1868921c-606d-44da-8cf7-2a03fb732681' };
      department.branch = branch;

      const branchCollection: IBranch[] = [{ id: '2f283267-4cf9-42d6-9301-9afa2ed13ca0' }];
      jest.spyOn(branchService, 'query').mockReturnValue(of(new HttpResponse({ body: branchCollection })));
      const additionalBranches = [branch];
      const expectedCollection: IBranch[] = [...additionalBranches, ...branchCollection];
      jest.spyOn(branchService, 'addBranchToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ department });
      comp.ngOnInit();

      expect(branchService.query).toHaveBeenCalled();
      expect(branchService.addBranchToCollectionIfMissing).toHaveBeenCalledWith(
        branchCollection,
        ...additionalBranches.map(expect.objectContaining)
      );
      expect(comp.branchesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const department: IDepartment = { id: 'CBA' };
      const userproifle: IUserprofile = { id: 'ab2e5251-0555-448d-9908-86137a330acf' };
      department.userproifle = userproifle;
      const branch: IBranch = { id: '04d10e20-0fe7-4e42-989c-df0c58c657b7' };
      department.branch = branch;

      activatedRoute.data = of({ department });
      comp.ngOnInit();

      expect(comp.userprofilesSharedCollection).toContain(userproifle);
      expect(comp.branchesSharedCollection).toContain(branch);
      expect(comp.department).toEqual(department);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDepartment>>();
      const department = { id: 'ABC' };
      jest.spyOn(departmentFormService, 'getDepartment').mockReturnValue(department);
      jest.spyOn(departmentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ department });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: department }));
      saveSubject.complete();

      // THEN
      expect(departmentFormService.getDepartment).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(departmentService.update).toHaveBeenCalledWith(expect.objectContaining(department));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDepartment>>();
      const department = { id: 'ABC' };
      jest.spyOn(departmentFormService, 'getDepartment').mockReturnValue({ id: null });
      jest.spyOn(departmentService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ department: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: department }));
      saveSubject.complete();

      // THEN
      expect(departmentFormService.getDepartment).toHaveBeenCalled();
      expect(departmentService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDepartment>>();
      const department = { id: 'ABC' };
      jest.spyOn(departmentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ department });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(departmentService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareUserprofile', () => {
      it('Should forward to userprofileService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(userprofileService, 'compareUserprofile');
        comp.compareUserprofile(entity, entity2);
        expect(userprofileService.compareUserprofile).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareBranch', () => {
      it('Should forward to branchService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(branchService, 'compareBranch');
        comp.compareBranch(entity, entity2);
        expect(branchService.compareBranch).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
