import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { FraudInvestigationReportFormService, FraudInvestigationReportFormGroup } from './fraud-investigation-report-form.service';
import { IFraudInvestigationReport } from '../fraud-investigation-report.model';
import { FraudInvestigationReportService } from '../service/fraud-investigation-report.service';
import { IFraudKnowledgeManagement } from 'app/entities/fraud-knowledge-management/fraud-knowledge-management.model';
import { FraudKnowledgeManagementService } from 'app/entities/fraud-knowledge-management/service/fraud-knowledge-management.service';

@Component({
  selector: 'jhi-fraud-investigation-report-update',
  templateUrl: './fraud-investigation-report-update.component.html',
})
export class FraudInvestigationReportUpdateComponent implements OnInit {
  isSaving = false;
  fraudInvestigationReport: IFraudInvestigationReport | null = null;

  fraudKnowledgeManagementsSharedCollection: IFraudKnowledgeManagement[] = [];

  editForm: FraudInvestigationReportFormGroup = this.fraudInvestigationReportFormService.createFraudInvestigationReportFormGroup();

  constructor(
    protected fraudInvestigationReportService: FraudInvestigationReportService,
    protected fraudInvestigationReportFormService: FraudInvestigationReportFormService,
    protected fraudKnowledgeManagementService: FraudKnowledgeManagementService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareFraudKnowledgeManagement = (o1: IFraudKnowledgeManagement | null, o2: IFraudKnowledgeManagement | null): boolean =>
    this.fraudKnowledgeManagementService.compareFraudKnowledgeManagement(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fraudInvestigationReport }) => {
      this.fraudInvestigationReport = fraudInvestigationReport;
      if (fraudInvestigationReport) {
        this.updateForm(fraudInvestigationReport);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const fraudInvestigationReport = this.fraudInvestigationReportFormService.getFraudInvestigationReport(this.editForm);
    if (fraudInvestigationReport.id !== null) {
      this.subscribeToSaveResponse(this.fraudInvestigationReportService.update(fraudInvestigationReport));
    } else {
      this.subscribeToSaveResponse(this.fraudInvestigationReportService.create(fraudInvestigationReport));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFraudInvestigationReport>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(fraudInvestigationReport: IFraudInvestigationReport): void {
    this.fraudInvestigationReport = fraudInvestigationReport;
    this.fraudInvestigationReportFormService.resetForm(this.editForm, fraudInvestigationReport);

    this.fraudKnowledgeManagementsSharedCollection =
      this.fraudKnowledgeManagementService.addFraudKnowledgeManagementToCollectionIfMissing<IFraudKnowledgeManagement>(
        this.fraudKnowledgeManagementsSharedCollection,
        fraudInvestigationReport.fraudType
      );
  }

  protected loadRelationshipsOptions(): void {
    this.fraudKnowledgeManagementService
      .query()
      .pipe(map((res: HttpResponse<IFraudKnowledgeManagement[]>) => res.body ?? []))
      .pipe(
        map((fraudKnowledgeManagements: IFraudKnowledgeManagement[]) =>
          this.fraudKnowledgeManagementService.addFraudKnowledgeManagementToCollectionIfMissing<IFraudKnowledgeManagement>(
            fraudKnowledgeManagements,
            this.fraudInvestigationReport?.fraudType
          )
        )
      )
      .subscribe(
        (fraudKnowledgeManagements: IFraudKnowledgeManagement[]) =>
          (this.fraudKnowledgeManagementsSharedCollection = fraudKnowledgeManagements)
      );
  }
}
