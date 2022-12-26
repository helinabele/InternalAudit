import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { DivisionFormService, DivisionFormGroup } from './division-form.service';
import { IDivision } from '../division.model';
import { DivisionService } from '../service/division.service';
import { IUserprofile } from 'app/entities/userprofile/userprofile.model';
import { UserprofileService } from 'app/entities/userprofile/service/userprofile.service';
import { IDepartment } from 'app/entities/department/department.model';
import { DepartmentService } from 'app/entities/department/service/department.service';

@Component({
  selector: 'jhi-division-update',
  templateUrl: './division-update.component.html',
})
export class DivisionUpdateComponent implements OnInit {
  isSaving = false;
  division: IDivision | null = null;

  userprofilesSharedCollection: IUserprofile[] = [];
  departmentsSharedCollection: IDepartment[] = [];

  editForm: DivisionFormGroup = this.divisionFormService.createDivisionFormGroup();

  constructor(
    protected divisionService: DivisionService,
    protected divisionFormService: DivisionFormService,
    protected userprofileService: UserprofileService,
    protected departmentService: DepartmentService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareUserprofile = (o1: IUserprofile | null, o2: IUserprofile | null): boolean => this.userprofileService.compareUserprofile(o1, o2);

  compareDepartment = (o1: IDepartment | null, o2: IDepartment | null): boolean => this.departmentService.compareDepartment(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ division }) => {
      this.division = division;
      if (division) {
        this.updateForm(division);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const division = this.divisionFormService.getDivision(this.editForm);
    if (division.id !== null) {
      this.subscribeToSaveResponse(this.divisionService.update(division));
    } else {
      this.subscribeToSaveResponse(this.divisionService.create(division));
    }
  }

  onClickChange() {}

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDivision>>): void {
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

  protected updateForm(division: IDivision): void {
    this.division = division;
    this.divisionFormService.resetForm(this.editForm, division);

    this.userprofilesSharedCollection = this.userprofileService.addUserprofileToCollectionIfMissing<IUserprofile>(
      this.userprofilesSharedCollection,
      division.userproifle
    );
    this.departmentsSharedCollection = this.departmentService.addDepartmentToCollectionIfMissing<IDepartment>(
      this.departmentsSharedCollection,
      division.department
    );
  }

  protected loadRelationshipsOptions(): void {
    this.userprofileService
      .query()
      .pipe(map((res: HttpResponse<IUserprofile[]>) => res.body ?? []))
      .pipe(
        map((userprofiles: IUserprofile[]) =>
          this.userprofileService.addUserprofileToCollectionIfMissing<IUserprofile>(userprofiles, this.division?.userproifle)
        )
      )
      .subscribe((userprofiles: IUserprofile[]) => (this.userprofilesSharedCollection = userprofiles));

    this.departmentService
      .query()
      .pipe(map((res: HttpResponse<IDepartment[]>) => res.body ?? []))
      .pipe(
        map((departments: IDepartment[]) =>
          this.departmentService.addDepartmentToCollectionIfMissing<IDepartment>(departments, this.division?.department)
        )
      )
      .subscribe((departments: IDepartment[]) => (this.departmentsSharedCollection = departments));
  }
}
