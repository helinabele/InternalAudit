import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { FraudKnowledgeManagementFormService, FraudKnowledgeManagementFormGroup } from './fraud-knowledge-management-form.service';
import { IFraudKnowledgeManagement } from '../fraud-knowledge-management.model';
import { FraudKnowledgeManagementService } from '../service/fraud-knowledge-management.service';

@Component({
  selector: 'jhi-fraud-knowledge-management-update',
  templateUrl: './fraud-knowledge-management-update.component.html',
})
export class FraudKnowledgeManagementUpdateComponent implements OnInit {
  isSaving = false;
  fraudKnowledgeManagement: IFraudKnowledgeManagement | null = null;

  editForm: FraudKnowledgeManagementFormGroup = this.fraudKnowledgeManagementFormService.createFraudKnowledgeManagementFormGroup();

  constructor(
    protected fraudKnowledgeManagementService: FraudKnowledgeManagementService,
    protected fraudKnowledgeManagementFormService: FraudKnowledgeManagementFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fraudKnowledgeManagement }) => {
      this.fraudKnowledgeManagement = fraudKnowledgeManagement;
      if (fraudKnowledgeManagement) {
        this.updateForm(fraudKnowledgeManagement);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const fraudKnowledgeManagement = this.fraudKnowledgeManagementFormService.getFraudKnowledgeManagement(this.editForm);
    if (fraudKnowledgeManagement.id !== null) {
      this.subscribeToSaveResponse(this.fraudKnowledgeManagementService.update(fraudKnowledgeManagement));
    } else {
      this.subscribeToSaveResponse(this.fraudKnowledgeManagementService.create(fraudKnowledgeManagement));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFraudKnowledgeManagement>>): void {
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

  protected updateForm(fraudKnowledgeManagement: IFraudKnowledgeManagement): void {
    this.fraudKnowledgeManagement = fraudKnowledgeManagement;
    this.fraudKnowledgeManagementFormService.resetForm(this.editForm, fraudKnowledgeManagement);
  }
}
