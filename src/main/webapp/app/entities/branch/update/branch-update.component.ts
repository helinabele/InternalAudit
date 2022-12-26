import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { BranchFormService, BranchFormGroup } from './branch-form.service';
import { IBranch } from '../branch.model';
import { BranchService } from '../service/branch.service';
import { IUserprofile } from 'app/entities/userprofile/userprofile.model';
import { UserprofileService } from 'app/entities/userprofile/service/userprofile.service';
import { IRegion } from 'app/entities/region/region.model';
import { RegionService } from 'app/entities/region/service/region.service';

@Component({
  selector: 'jhi-branch-update',
  templateUrl: './branch-update.component.html',
})
export class BranchUpdateComponent implements OnInit {
  isSaving = false;
  branch: IBranch | null = null;

  userprofilesSharedCollection: IUserprofile[] = [];
  regionsSharedCollection: IRegion[] = [];

  editForm: BranchFormGroup = this.branchFormService.createBranchFormGroup();

  constructor(
    protected branchService: BranchService,
    protected branchFormService: BranchFormService,
    protected userprofileService: UserprofileService,
    protected regionService: RegionService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareUserprofile = (o1: IUserprofile | null, o2: IUserprofile | null): boolean => this.userprofileService.compareUserprofile(o1, o2);

  compareRegion = (o1: IRegion | null, o2: IRegion | null): boolean => this.regionService.compareRegion(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ branch }) => {
      this.branch = branch;
      if (branch) {
        this.updateForm(branch);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const branch = this.branchFormService.getBranch(this.editForm);
    if (branch.id !== null) {
      this.subscribeToSaveResponse(this.branchService.update(branch));
    } else {
      this.subscribeToSaveResponse(this.branchService.create(branch));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBranch>>): void {
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

  protected updateForm(branch: IBranch): void {
    this.branch = branch;
    this.branchFormService.resetForm(this.editForm, branch);

    this.userprofilesSharedCollection = this.userprofileService.addUserprofileToCollectionIfMissing<IUserprofile>(
      this.userprofilesSharedCollection,
      branch.userproifle
    );
    this.regionsSharedCollection = this.regionService.addRegionToCollectionIfMissing<IRegion>(this.regionsSharedCollection, branch.region);
  }

  protected loadRelationshipsOptions(): void {
    this.userprofileService
      .query()
      .pipe(map((res: HttpResponse<IUserprofile[]>) => res.body ?? []))
      .pipe(
        map((userprofiles: IUserprofile[]) =>
          this.userprofileService.addUserprofileToCollectionIfMissing<IUserprofile>(userprofiles, this.branch?.userproifle)
        )
      )
      .subscribe((userprofiles: IUserprofile[]) => (this.userprofilesSharedCollection = userprofiles));

    this.regionService
      .query()
      .pipe(map((res: HttpResponse<IRegion[]>) => res.body ?? []))
      .pipe(map((regions: IRegion[]) => this.regionService.addRegionToCollectionIfMissing<IRegion>(regions, this.branch?.region)))
      .subscribe((regions: IRegion[]) => (this.regionsSharedCollection = regions));
  }
}
