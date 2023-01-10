import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { DepartmentFormService, DepartmentFormGroup } from './department-form.service';
import { IDepartment } from '../department.model';
import { DepartmentService } from '../service/department.service';
import { IUserprofile } from 'app/entities/userprofile/userprofile.model';
import { UserprofileService } from 'app/entities/userprofile/service/userprofile.service';
import { IBranch } from 'app/entities/branch/branch.model';
import { BranchService } from 'app/entities/branch/service/branch.service';

@Component({
  selector: 'jhi-department-update',
  templateUrl: './department-update.component.html',
})
export class DepartmentUpdateComponent implements OnInit {
  isSaving = false;
  department: IDepartment | null = null;

  userprofilesSharedCollection: IUserprofile[] = [];
  branchesSharedCollection: IBranch[] = [];

  editForm: DepartmentFormGroup = this.departmentFormService.createDepartmentFormGroup();

  constructor(
    protected departmentService: DepartmentService,
    protected departmentFormService: DepartmentFormService,
    protected userprofileService: UserprofileService,
    protected branchService: BranchService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareUserprofile = (o1: IUserprofile | null, o2: IUserprofile | null): boolean => this.userprofileService.compareUserprofile(o1, o2);

  compareBranch = (o1: IBranch | null, o2: IBranch | null): boolean => this.branchService.compareBranch(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ department }) => {
      this.department = department;
      if (department) {
        this.updateForm(department);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const department = this.departmentFormService.getDepartment(this.editForm);
    if (department.id !== null) {
      this.subscribeToSaveResponse(this.departmentService.update(department));
    } else {
      this.subscribeToSaveResponse(this.departmentService.create(department));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDepartment>>): void {
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

  protected updateForm(department: IDepartment): void {
    this.department = department;
    this.departmentFormService.resetForm(this.editForm, department);

    this.userprofilesSharedCollection = this.userprofileService.addUserprofileToCollectionIfMissing<IUserprofile>(
      this.userprofilesSharedCollection,
      department.userproifle
    );
    this.branchesSharedCollection = this.branchService.addBranchToCollectionIfMissing<IBranch>(
      this.branchesSharedCollection,
      department.branch
    );
  }

  protected loadRelationshipsOptions(): void {
    this.userprofileService
      .query()
      .pipe(map((res: HttpResponse<IUserprofile[]>) => res.body ?? []))
      .pipe(
        map((userprofiles: IUserprofile[]) =>
          this.userprofileService.addUserprofileToCollectionIfMissing<IUserprofile>(userprofiles, this.department?.userproifle)
        )
      )
      .subscribe((userprofiles: IUserprofile[]) => (this.userprofilesSharedCollection = userprofiles));

    this.branchService
      .query()
      .pipe(map((res: HttpResponse<IBranch[]>) => res.body ?? []))
      .pipe(map((branches: IBranch[]) => this.branchService.addBranchToCollectionIfMissing<IBranch>(branches, this.department?.branch)))
      .subscribe((branches: IBranch[]) => (this.branchesSharedCollection = branches));
  }
}
