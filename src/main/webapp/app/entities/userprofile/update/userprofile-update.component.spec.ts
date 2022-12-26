import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { UserprofileFormService } from './userprofile-form.service';
import { UserprofileService } from '../service/userprofile.service';
import { IUserprofile } from '../userprofile.model';

import { UserprofileUpdateComponent } from './userprofile-update.component';

describe('Userprofile Management Update Component', () => {
  let comp: UserprofileUpdateComponent;
  let fixture: ComponentFixture<UserprofileUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let userprofileFormService: UserprofileFormService;
  let userprofileService: UserprofileService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [UserprofileUpdateComponent],
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
      .overrideTemplate(UserprofileUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(UserprofileUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    userprofileFormService = TestBed.inject(UserprofileFormService);
    userprofileService = TestBed.inject(UserprofileService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const userprofile: IUserprofile = { id: 'CBA' };

      activatedRoute.data = of({ userprofile });
      comp.ngOnInit();

      expect(comp.userprofile).toEqual(userprofile);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IUserprofile>>();
      const userprofile = { id: 'ABC' };
      jest.spyOn(userprofileFormService, 'getUserprofile').mockReturnValue(userprofile);
      jest.spyOn(userprofileService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userprofile });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: userprofile }));
      saveSubject.complete();

      // THEN
      expect(userprofileFormService.getUserprofile).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(userprofileService.update).toHaveBeenCalledWith(expect.objectContaining(userprofile));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IUserprofile>>();
      const userprofile = { id: 'ABC' };
      jest.spyOn(userprofileFormService, 'getUserprofile').mockReturnValue({ id: null });
      jest.spyOn(userprofileService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userprofile: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: userprofile }));
      saveSubject.complete();

      // THEN
      expect(userprofileFormService.getUserprofile).toHaveBeenCalled();
      expect(userprofileService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IUserprofile>>();
      const userprofile = { id: 'ABC' };
      jest.spyOn(userprofileService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userprofile });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(userprofileService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
