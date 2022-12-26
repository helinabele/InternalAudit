import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IBranch, NewBranch } from '../branch.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IBranch for edit and NewBranchFormGroupInput for create.
 */
type BranchFormGroupInput = IBranch | PartialWithRequiredKeyOf<NewBranch>;

type BranchFormDefaults = Pick<NewBranch, 'id'>;

type BranchFormGroupContent = {
  id: FormControl<IBranch['id'] | NewBranch['id']>;
  branchName: FormControl<IBranch['branchName']>;
  branchId: FormControl<IBranch['branchId']>;
  description: FormControl<IBranch['description']>;
  userproifle: FormControl<IBranch['userproifle']>;
  region: FormControl<IBranch['region']>;
};

export type BranchFormGroup = FormGroup<BranchFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class BranchFormService {
  createBranchFormGroup(branch: BranchFormGroupInput = { id: null }): BranchFormGroup {
    const branchRawValue = {
      ...this.getFormDefaults(),
      ...branch,
    };
    return new FormGroup<BranchFormGroupContent>({
      id: new FormControl(
        { value: branchRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      branchName: new FormControl(branchRawValue.branchName),
      branchId: new FormControl(branchRawValue.branchId),
      description: new FormControl(branchRawValue.description),
      userproifle: new FormControl(branchRawValue.userproifle),
      region: new FormControl(branchRawValue.region),
    });
  }

  getBranch(form: BranchFormGroup): IBranch | NewBranch {
    return form.getRawValue() as IBranch | NewBranch;
  }

  resetForm(form: BranchFormGroup, branch: BranchFormGroupInput): void {
    const branchRawValue = { ...this.getFormDefaults(), ...branch };
    form.reset(
      {
        ...branchRawValue,
        id: { value: branchRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): BranchFormDefaults {
    return {
      id: null,
    };
  }
}
