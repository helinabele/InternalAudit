import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { TaskFormService, TaskFormGroup } from './task-form.service';
import { ITask } from '../task.model';
import { TaskService } from '../service/task.service';
import { IAssignTask } from 'app/entities/assign-task/assign-task.model';
import { AssignTaskService } from 'app/entities/assign-task/service/assign-task.service';

@Component({
  selector: 'jhi-task-update',
  templateUrl: './task-update.component.html',
})
export class TaskUpdateComponent implements OnInit {
  isSaving = false;
  task: ITask | null = null;

  assignTasksCollection: IAssignTask[] = [];

  editForm: TaskFormGroup = this.taskFormService.createTaskFormGroup();

  constructor(
    protected taskService: TaskService,
    protected taskFormService: TaskFormService,
    protected assignTaskService: AssignTaskService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareAssignTask = (o1: IAssignTask | null, o2: IAssignTask | null): boolean => this.assignTaskService.compareAssignTask(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ task }) => {
      this.task = task;
      if (task) {
        this.updateForm(task);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const task = this.taskFormService.getTask(this.editForm);
    if (task.id !== null) {
      this.subscribeToSaveResponse(this.taskService.update(task));
    } else {
      this.subscribeToSaveResponse(this.taskService.create(task));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITask>>): void {
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

  protected updateForm(task: ITask): void {
    this.task = task;
    this.taskFormService.resetForm(this.editForm, task);

    this.assignTasksCollection = this.assignTaskService.addAssignTaskToCollectionIfMissing<IAssignTask>(
      this.assignTasksCollection,
      task.assignTask
    );
  }

  protected loadRelationshipsOptions(): void {
    this.assignTaskService
      .query({ filter: 'task-is-null' })
      .pipe(map((res: HttpResponse<IAssignTask[]>) => res.body ?? []))
      .pipe(
        map((assignTasks: IAssignTask[]) =>
          this.assignTaskService.addAssignTaskToCollectionIfMissing<IAssignTask>(assignTasks, this.task?.assignTask)
        )
      )
      .subscribe((assignTasks: IAssignTask[]) => (this.assignTasksCollection = assignTasks));
  }
}
