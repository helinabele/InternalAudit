import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { AssignTaskFormService } from './assign-task-form.service';
import { AssignTaskService } from '../service/assign-task.service';
import { IAssignTask } from '../assign-task.model';

import { AssignTaskUpdateComponent } from './assign-task-update.component';

describe('AssignTask Management Update Component', () => {
  let comp: AssignTaskUpdateComponent;
  let fixture: ComponentFixture<AssignTaskUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let assignTaskFormService: AssignTaskFormService;
  let assignTaskService: AssignTaskService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [AssignTaskUpdateComponent],
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
      .overrideTemplate(AssignTaskUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AssignTaskUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    assignTaskFormService = TestBed.inject(AssignTaskFormService);
    assignTaskService = TestBed.inject(AssignTaskService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const assignTask: IAssignTask = { id: 'CBA' };

      activatedRoute.data = of({ assignTask });
      comp.ngOnInit();

      expect(comp.assignTask).toEqual(assignTask);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAssignTask>>();
      const assignTask = { id: 'ABC' };
      jest.spyOn(assignTaskFormService, 'getAssignTask').mockReturnValue(assignTask);
      jest.spyOn(assignTaskService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ assignTask });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: assignTask }));
      saveSubject.complete();

      // THEN
      expect(assignTaskFormService.getAssignTask).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(assignTaskService.update).toHaveBeenCalledWith(expect.objectContaining(assignTask));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAssignTask>>();
      const assignTask = { id: 'ABC' };
      jest.spyOn(assignTaskFormService, 'getAssignTask').mockReturnValue({ id: null });
      jest.spyOn(assignTaskService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ assignTask: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: assignTask }));
      saveSubject.complete();

      // THEN
      expect(assignTaskFormService.getAssignTask).toHaveBeenCalled();
      expect(assignTaskService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAssignTask>>();
      const assignTask = { id: 'ABC' };
      jest.spyOn(assignTaskService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ assignTask });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(assignTaskService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
