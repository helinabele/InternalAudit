import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { RegionFormService } from './region-form.service';
import { RegionService } from '../service/region.service';
import { IRegion } from '../region.model';
import { IUserprofile } from 'app/entities/userprofile/userprofile.model';
import { UserprofileService } from 'app/entities/userprofile/service/userprofile.service';

import { RegionUpdateComponent } from './region-update.component';

describe('Region Management Update Component', () => {
  let comp: RegionUpdateComponent;
  let fixture: ComponentFixture<RegionUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let regionFormService: RegionFormService;
  let regionService: RegionService;
  let userprofileService: UserprofileService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [RegionUpdateComponent],
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
      .overrideTemplate(RegionUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RegionUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    regionFormService = TestBed.inject(RegionFormService);
    regionService = TestBed.inject(RegionService);
    userprofileService = TestBed.inject(UserprofileService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Userprofile query and add missing value', () => {
      const region: IRegion = { id: 'CBA' };
      const userproifle: IUserprofile = { id: '3eadab6d-6fc7-4586-bd1b-4cb4bcf17589' };
      region.userproifle = userproifle;

      const userprofileCollection: IUserprofile[] = [{ id: '0fb5316e-ce71-41f3-a5ca-b3bfd63b833a' }];
      jest.spyOn(userprofileService, 'query').mockReturnValue(of(new HttpResponse({ body: userprofileCollection })));
      const additionalUserprofiles = [userproifle];
      const expectedCollection: IUserprofile[] = [...additionalUserprofiles, ...userprofileCollection];
      jest.spyOn(userprofileService, 'addUserprofileToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ region });
      comp.ngOnInit();

      expect(userprofileService.query).toHaveBeenCalled();
      expect(userprofileService.addUserprofileToCollectionIfMissing).toHaveBeenCalledWith(
        userprofileCollection,
        ...additionalUserprofiles.map(expect.objectContaining)
      );
      expect(comp.userprofilesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const region: IRegion = { id: 'CBA' };
      const userproifle: IUserprofile = { id: 'e23ad325-ae8f-4af1-bbae-761d5cfdc0ec' };
      region.userproifle = userproifle;

      activatedRoute.data = of({ region });
      comp.ngOnInit();

      expect(comp.userprofilesSharedCollection).toContain(userproifle);
      expect(comp.region).toEqual(region);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRegion>>();
      const region = { id: 'ABC' };
      jest.spyOn(regionFormService, 'getRegion').mockReturnValue(region);
      jest.spyOn(regionService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ region });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: region }));
      saveSubject.complete();

      // THEN
      expect(regionFormService.getRegion).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(regionService.update).toHaveBeenCalledWith(expect.objectContaining(region));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRegion>>();
      const region = { id: 'ABC' };
      jest.spyOn(regionFormService, 'getRegion').mockReturnValue({ id: null });
      jest.spyOn(regionService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ region: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: region }));
      saveSubject.complete();

      // THEN
      expect(regionFormService.getRegion).toHaveBeenCalled();
      expect(regionService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRegion>>();
      const region = { id: 'ABC' };
      jest.spyOn(regionService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ region });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(regionService.update).toHaveBeenCalled();
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
  });
});
