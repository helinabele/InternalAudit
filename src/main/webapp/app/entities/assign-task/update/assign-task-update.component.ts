import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { AssignTaskFormService, AssignTaskFormGroup } from './assign-task-form.service';
import { IAssignTask } from '../assign-task.model';
import { AssignTaskService } from '../service/assign-task.service';

@Component({
  selector: 'jhi-assign-task-update',
  templateUrl: './assign-task-update.component.html',
})
export class AssignTaskUpdateComponent implements OnInit {
  isSaving = false;
  assignTask: IAssignTask | null = null;

  editForm: AssignTaskFormGroup = this.assignTaskFormService.createAssignTaskFormGroup();

  constructor(
    protected assignTaskService: AssignTaskService,
    protected assignTaskFormService: AssignTaskFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ assignTask }) => {
      this.assignTask = assignTask;
      if (assignTask) {
        this.updateForm(assignTask);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const assignTask = this.assignTaskFormService.getAssignTask(this.editForm);
    if (assignTask.id !== null) {
      this.subscribeToSaveResponse(this.assignTaskService.update(assignTask));
    } else {
      this.subscribeToSaveResponse(this.assignTaskService.create(assignTask));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAssignTask>>): void {
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

  protected updateForm(assignTask: IAssignTask): void {
    this.assignTask = assignTask;
    this.assignTaskFormService.resetForm(this.editForm, assignTask);
  }
}
