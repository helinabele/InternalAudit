import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { FraudInvestigationReportFormService } from './fraud-investigation-report-form.service';
import { FraudInvestigationReportService } from '../service/fraud-investigation-report.service';
import { IFraudInvestigationReport } from '../fraud-investigation-report.model';
import { IFraudKnowledgeManagement } from 'app/entities/fraud-knowledge-management/fraud-knowledge-management.model';
import { FraudKnowledgeManagementService } from 'app/entities/fraud-knowledge-management/service/fraud-knowledge-management.service';

import { FraudInvestigationReportUpdateComponent } from './fraud-investigation-report-update.component';

describe('FraudInvestigationReport Management Update Component', () => {
  let comp: FraudInvestigationReportUpdateComponent;
  let fixture: ComponentFixture<FraudInvestigationReportUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let fraudInvestigationReportFormService: FraudInvestigationReportFormService;
  let fraudInvestigationReportService: FraudInvestigationReportService;
  let fraudKnowledgeManagementService: FraudKnowledgeManagementService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [FraudInvestigationReportUpdateComponent],
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
      .overrideTemplate(FraudInvestigationReportUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FraudInvestigationReportUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    fraudInvestigationReportFormService = TestBed.inject(FraudInvestigationReportFormService);
    fraudInvestigationReportService = TestBed.inject(FraudInvestigationReportService);
    fraudKnowledgeManagementService = TestBed.inject(FraudKnowledgeManagementService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call FraudKnowledgeManagement query and add missing value', () => {
      const fraudInvestigationReport: IFraudInvestigationReport = { id: 'CBA' };
      const fraudType: IFraudKnowledgeManagement = { id: 'e4e47949-9235-40d8-b8cb-16f41ee50e83' };
      fraudInvestigationReport.fraudType = fraudType;

      const fraudKnowledgeManagementCollection: IFraudKnowledgeManagement[] = [{ id: '454017fc-2985-4b6c-9cdc-584423e1d288' }];
      jest
        .spyOn(fraudKnowledgeManagementService, 'query')
        .mockReturnValue(of(new HttpResponse({ body: fraudKnowledgeManagementCollection })));
      const additionalFraudKnowledgeManagements = [fraudType];
      const expectedCollection: IFraudKnowledgeManagement[] = [
        ...additionalFraudKnowledgeManagements,
        ...fraudKnowledgeManagementCollection,
      ];
      jest.spyOn(fraudKnowledgeManagementService, 'addFraudKnowledgeManagementToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ fraudInvestigationReport });
      comp.ngOnInit();

      expect(fraudKnowledgeManagementService.query).toHaveBeenCalled();
      expect(fraudKnowledgeManagementService.addFraudKnowledgeManagementToCollectionIfMissing).toHaveBeenCalledWith(
        fraudKnowledgeManagementCollection,
        ...additionalFraudKnowledgeManagements.map(expect.objectContaining)
      );
      expect(comp.fraudKnowledgeManagementsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const fraudInvestigationReport: IFraudInvestigationReport = { id: 'CBA' };
      const fraudType: IFraudKnowledgeManagement = { id: 'a09249ba-13cf-471a-b69c-eef7ba703aa4' };
      fraudInvestigationReport.fraudType = fraudType;

      activatedRoute.data = of({ fraudInvestigationReport });
      comp.ngOnInit();

      expect(comp.fraudKnowledgeManagementsSharedCollection).toContain(fraudType);
      expect(comp.fraudInvestigationReport).toEqual(fraudInvestigationReport);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFraudInvestigationReport>>();
      const fraudInvestigationReport = { id: 'ABC' };
      jest.spyOn(fraudInvestigationReportFormService, 'getFraudInvestigationReport').mockReturnValue(fraudInvestigationReport);
      jest.spyOn(fraudInvestigationReportService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ fraudInvestigationReport });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: fraudInvestigationReport }));
      saveSubject.complete();

      // THEN
      expect(fraudInvestigationReportFormService.getFraudInvestigationReport).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(fraudInvestigationReportService.update).toHaveBeenCalledWith(expect.objectContaining(fraudInvestigationReport));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFraudInvestigationReport>>();
      const fraudInvestigationReport = { id: 'ABC' };
      jest.spyOn(fraudInvestigationReportFormService, 'getFraudInvestigationReport').mockReturnValue({ id: null });
      jest.spyOn(fraudInvestigationReportService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ fraudInvestigationReport: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: fraudInvestigationReport }));
      saveSubject.complete();

      // THEN
      expect(fraudInvestigationReportFormService.getFraudInvestigationReport).toHaveBeenCalled();
      expect(fraudInvestigationReportService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFraudInvestigationReport>>();
      const fraudInvestigationReport = { id: 'ABC' };
      jest.spyOn(fraudInvestigationReportService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ fraudInvestigationReport });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(fraudInvestigationReportService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareFraudKnowledgeManagement', () => {
      it('Should forward to fraudKnowledgeManagementService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(fraudKnowledgeManagementService, 'compareFraudKnowledgeManagement');
        comp.compareFraudKnowledgeManagement(entity, entity2);
        expect(fraudKnowledgeManagementService.compareFraudKnowledgeManagement).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
