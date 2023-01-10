import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { UserprofileFormService, UserprofileFormGroup } from './userprofile-form.service';
import { IUserprofile } from '../userprofile.model';
import { UserprofileService } from '../service/userprofile.service';
import { UserStatus } from 'app/entities/enumerations/user-status.model';

@Component({
  selector: 'jhi-userprofile-update',
  templateUrl: './userprofile-update.component.html',
})
export class UserprofileUpdateComponent implements OnInit {
  isSaving = false;
  userprofile: IUserprofile | null = null;
  userStatusValues = Object.keys(UserStatus);

  editForm: UserprofileFormGroup = this.userprofileFormService.createUserprofileFormGroup();

  constructor(
    protected userprofileService: UserprofileService,
    protected userprofileFormService: UserprofileFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userprofile }) => {
      this.userprofile = userprofile;
      if (userprofile) {
        this.updateForm(userprofile);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const userprofile = this.userprofileFormService.getUserprofile(this.editForm);
    if (userprofile.id !== null) {
      this.subscribeToSaveResponse(this.userprofileService.update(userprofile));
    } else {
      this.subscribeToSaveResponse(this.userprofileService.create(userprofile));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserprofile>>): void {
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

  protected updateForm(userprofile: IUserprofile): void {
    this.userprofile = userprofile;
    this.userprofileFormService.resetForm(this.editForm, userprofile);
  }
}
