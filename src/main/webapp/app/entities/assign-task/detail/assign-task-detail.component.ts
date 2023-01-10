import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAssignTask } from '../assign-task.model';

@Component({
  selector: 'jhi-assign-task-detail',
  templateUrl: './assign-task-detail.component.html',
})
export class AssignTaskDetailComponent implements OnInit {
  assignTask: IAssignTask | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ assignTask }) => {
      this.assignTask = assignTask;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
