import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { BranchFormService } from './branch-form.service';
import { BranchService } from '../service/branch.service';
import { IBranch } from '../branch.model';
import { IUserprofile } from 'app/entities/userprofile/userprofile.model';
import { UserprofileService } from 'app/entities/userprofile/service/userprofile.service';
import { IRegion } from 'app/entities/region/region.model';
import { RegionService } from 'app/entities/region/service/region.service';

import { BranchUpdateComponent } from './branch-update.component';

describe('Branch Management Update Component', () => {
  let comp: BranchUpdateComponent;
  let fixture: ComponentFixture<BranchUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let branchFormService: BranchFormService;
  let branchService: BranchService;
  let userprofileService: UserprofileService;
  let regionService: RegionService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [BranchUpdateComponent],
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
      .overrideTemplate(BranchUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(BranchUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    branchFormService = TestBed.inject(BranchFormService);
    branchService = TestBed.inject(BranchService);
    userprofileService = TestBed.inject(UserprofileService);
    regionService = TestBed.inject(RegionService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Userprofile query and add missing value', () => {
      const branch: IBranch = { id: 'CBA' };
      const userproifle: IUserprofile = { id: 'b1a131f5-87dc-42ef-85bd-8a138ef26496' };
      branch.userproifle = userproifle;

      const userprofileCollection: IUserprofile[] = [{ id: '6eeaff85-8b6d-43f1-9032-6cd2912455c7' }];
      jest.spyOn(userprofileService, 'query').mockReturnValue(of(new HttpResponse({ body: userprofileCollection })));
      const additionalUserprofiles = [userproifle];
      const expectedCollection: IUserprofile[] = [...additionalUserprofiles, ...userprofileCollection];
      jest.spyOn(userprofileService, 'addUserprofileToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ branch });
      comp.ngOnInit();

      expect(userprofileService.query).toHaveBeenCalled();
      expect(userprofileService.addUserprofileToCollectionIfMissing).toHaveBeenCalledWith(
        userprofileCollection,
        ...additionalUserprofiles.map(expect.objectContaining)
      );
      expect(comp.userprofilesSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Region query and add missing value', () => {
      const branch: IBranch = { id: 'CBA' };
      const region: IRegion = { id: 'aa0a6854-8353-48b1-ac9a-8c97e1a949bc' };
      branch.region = region;

      const regionCollection: IRegion[] = [{ id: '98c1ea14-60ff-45f0-8669-b7db56ad2a3a' }];
      jest.spyOn(regionService, 'query').mockReturnValue(of(new HttpResponse({ body: regionCollection })));
      const additionalRegions = [region];
      const expectedCollection: IRegion[] = [...additionalRegions, ...regionCollection];
      jest.spyOn(regionService, 'addRegionToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ branch });
      comp.ngOnInit();

      expect(regionService.query).toHaveBeenCalled();
      expect(regionService.addRegionToCollectionIfMissing).toHaveBeenCalledWith(
        regionCollection,
        ...additionalRegions.map(expect.objectContaining)
      );
      expect(comp.regionsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const branch: IBranch = { id: 'CBA' };
      const userproifle: IUserprofile = { id: '7796db49-d623-4b4e-b71c-43362a83c150' };
      branch.userproifle = userproifle;
      const region: IRegion = { id: '72bfb81d-4846-43b2-9133-16ae3a995efe' };
      branch.region = region;

      activatedRoute.data = of({ branch });
      comp.ngOnInit();

      expect(comp.userprofilesSharedCollection).toContain(userproifle);
      expect(comp.regionsSharedCollection).toContain(region);
      expect(comp.branch).toEqual(branch);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBranch>>();
      const branch = { id: 'ABC' };
      jest.spyOn(branchFormService, 'getBranch').mockReturnValue(branch);
      jest.spyOn(branchService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ branch });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: branch }));
      saveSubject.complete();

      // THEN
      expect(branchFormService.getBranch).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(branchService.update).toHaveBeenCalledWith(expect.objectContaining(branch));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBranch>>();
      const branch = { id: 'ABC' };
      jest.spyOn(branchFormService, 'getBranch').mockReturnValue({ id: null });
      jest.spyOn(branchService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ branch: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: branch }));
      saveSubject.complete();

      // THEN
      expect(branchFormService.getBranch).toHaveBeenCalled();
      expect(branchService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBranch>>();
      const branch = { id: 'ABC' };
      jest.spyOn(branchService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ branch });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(branchService.update).toHaveBeenCalled();
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

    describe('compareRegion', () => {
      it('Should forward to regionService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(regionService, 'compareRegion');
        comp.compareRegion(entity, entity2);
        expect(regionService.compareRegion).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
