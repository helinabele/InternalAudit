import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { FraudKnowledgeManagementFormService } from './fraud-knowledge-management-form.service';
import { FraudKnowledgeManagementService } from '../service/fraud-knowledge-management.service';
import { IFraudKnowledgeManagement } from '../fraud-knowledge-management.model';

import { FraudKnowledgeManagementUpdateComponent } from './fraud-knowledge-management-update.component';

describe('FraudKnowledgeManagement Management Update Component', () => {
  let comp: FraudKnowledgeManagementUpdateComponent;
  let fixture: ComponentFixture<FraudKnowledgeManagementUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let fraudKnowledgeManagementFormService: FraudKnowledgeManagementFormService;
  let fraudKnowledgeManagementService: FraudKnowledgeManagementService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [FraudKnowledgeManagementUpdateComponent],
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
      .overrideTemplate(FraudKnowledgeManagementUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FraudKnowledgeManagementUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    fraudKnowledgeManagementFormService = TestBed.inject(FraudKnowledgeManagementFormService);
    fraudKnowledgeManagementService = TestBed.inject(FraudKnowledgeManagementService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const fraudKnowledgeManagement: IFraudKnowledgeManagement = { id: 'CBA' };

      activatedRoute.data = of({ fraudKnowledgeManagement });
      comp.ngOnInit();

      expect(comp.fraudKnowledgeManagement).toEqual(fraudKnowledgeManagement);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFraudKnowledgeManagement>>();
      const fraudKnowledgeManagement = { id: 'ABC' };
      jest.spyOn(fraudKnowledgeManagementFormService, 'getFraudKnowledgeManagement').mockReturnValue(fraudKnowledgeManagement);
      jest.spyOn(fraudKnowledgeManagementService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ fraudKnowledgeManagement });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: fraudKnowledgeManagement }));
      saveSubject.complete();

      // THEN
      expect(fraudKnowledgeManagementFormService.getFraudKnowledgeManagement).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(fraudKnowledgeManagementService.update).toHaveBeenCalledWith(expect.objectContaining(fraudKnowledgeManagement));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFraudKnowledgeManagement>>();
      const fraudKnowledgeManagement = { id: 'ABC' };
      jest.spyOn(fraudKnowledgeManagementFormService, 'getFraudKnowledgeManagement').mockReturnValue({ id: null });
      jest.spyOn(fraudKnowledgeManagementService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ fraudKnowledgeManagement: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: fraudKnowledgeManagement }));
      saveSubject.complete();

      // THEN
      expect(fraudKnowledgeManagementFormService.getFraudKnowledgeManagement).toHaveBeenCalled();
      expect(fraudKnowledgeManagementService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFraudKnowledgeManagement>>();
      const fraudKnowledgeManagement = { id: 'ABC' };
      jest.spyOn(fraudKnowledgeManagementService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ fraudKnowledgeManagement });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(fraudKnowledgeManagementService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
