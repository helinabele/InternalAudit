import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DivisionFormService } from './division-form.service';
import { DivisionService } from '../service/division.service';
import { IDivision } from '../division.model';
import { IUserprofile } from 'app/entities/userprofile/userprofile.model';
import { UserprofileService } from 'app/entities/userprofile/service/userprofile.service';
import { IDepartment } from 'app/entities/department/department.model';
import { DepartmentService } from 'app/entities/department/service/department.service';

import { DivisionUpdateComponent } from './division-update.component';

describe('Division Management Update Component', () => {
  let comp: DivisionUpdateComponent;
  let fixture: ComponentFixture<DivisionUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let divisionFormService: DivisionFormService;
  let divisionService: DivisionService;
  let userprofileService: UserprofileService;
  let departmentService: DepartmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [DivisionUpdateComponent],
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
      .overrideTemplate(DivisionUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DivisionUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    divisionFormService = TestBed.inject(DivisionFormService);
    divisionService = TestBed.inject(DivisionService);
    userprofileService = TestBed.inject(UserprofileService);
    departmentService = TestBed.inject(DepartmentService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Userprofile query and add missing value', () => {
      const division: IDivision = { id: 'CBA' };
      const userproifle: IUserprofile = { id: '158cedb0-0f31-4539-a213-4d89f3c7f88b' };
      division.userproifle = userproifle;

      const userprofileCollection: IUserprofile[] = [{ id: '0844fcfa-2254-40c1-8437-68943438e956' }];
      jest.spyOn(userprofileService, 'query').mockReturnValue(of(new HttpResponse({ body: userprofileCollection })));
      const additionalUserprofiles = [userproifle];
      const expectedCollection: IUserprofile[] = [...additionalUserprofiles, ...userprofileCollection];
      jest.spyOn(userprofileService, 'addUserprofileToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ division });
      comp.ngOnInit();

      expect(userprofileService.query).toHaveBeenCalled();
      expect(userprofileService.addUserprofileToCollectionIfMissing).toHaveBeenCalledWith(
        userprofileCollection,
        ...additionalUserprofiles.map(expect.objectContaining)
      );
      expect(comp.userprofilesSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Department query and add missing value', () => {
      const division: IDivision = { id: 'CBA' };
      const department: IDepartment = { id: 'c0a5bdf9-dd22-4426-b7fb-2ae8dd5b74dd' };
      division.department = department;

      const departmentCollection: IDepartment[] = [{ id: '112c0f2c-a933-4959-a543-5dc369ce39e0' }];
      jest.spyOn(departmentService, 'query').mockReturnValue(of(new HttpResponse({ body: departmentCollection })));
      const additionalDepartments = [department];
      const expectedCollection: IDepartment[] = [...additionalDepartments, ...departmentCollection];
      jest.spyOn(departmentService, 'addDepartmentToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ division });
      comp.ngOnInit();

      expect(departmentService.query).toHaveBeenCalled();
      expect(departmentService.addDepartmentToCollectionIfMissing).toHaveBeenCalledWith(
        departmentCollection,
        ...additionalDepartments.map(expect.objectContaining)
      );
      expect(comp.departmentsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const division: IDivision = { id: 'CBA' };
      const userproifle: IUserprofile = { id: 'f98a8fe3-964f-42c3-a7ad-d7da3dd7910f' };
      division.userproifle = userproifle;
      const department: IDepartment = { id: 'b4c35642-9b65-4cb6-8660-08ba5b8e747d' };
      division.department = department;

      activatedRoute.data = of({ division });
      comp.ngOnInit();

      expect(comp.userprofilesSharedCollection).toContain(userproifle);
      expect(comp.departmentsSharedCollection).toContain(department);
      expect(comp.division).toEqual(division);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDivision>>();
      const division = { id: 'ABC' };
      jest.spyOn(divisionFormService, 'getDivision').mockReturnValue(division);
      jest.spyOn(divisionService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ division });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: division }));
      saveSubject.complete();

      // THEN
      expect(divisionFormService.getDivision).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(divisionService.update).toHaveBeenCalledWith(expect.objectContaining(division));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDivision>>();
      const division = { id: 'ABC' };
      jest.spyOn(divisionFormService, 'getDivision').mockReturnValue({ id: null });
      jest.spyOn(divisionService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ division: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: division }));
      saveSubject.complete();

      // THEN
      expect(divisionFormService.getDivision).toHaveBeenCalled();
      expect(divisionService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDivision>>();
      const division = { id: 'ABC' };
      jest.spyOn(divisionService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ division });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(divisionService.update).toHaveBeenCalled();
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

    describe('compareDepartment', () => {
      it('Should forward to departmentService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(departmentService, 'compareDepartment');
        comp.compareDepartment(entity, entity2);
        expect(departmentService.compareDepartment).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
