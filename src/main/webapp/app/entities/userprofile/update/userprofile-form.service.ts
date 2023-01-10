import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IUserprofile, NewUserprofile } from '../userprofile.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IUserprofile for edit and NewUserprofileFormGroupInput for create.
 */
type UserprofileFormGroupInput = IUserprofile | PartialWithRequiredKeyOf<NewUserprofile>;

type UserprofileFormDefaults = Pick<NewUserprofile, 'id'>;

type UserprofileFormGroupContent = {
  id: FormControl<IUserprofile['id'] | NewUserprofile['id']>;
  regionId: FormControl<IUserprofile['regionId']>;
  branchId: FormControl<IUserprofile['branchId']>;
  departmentId: FormControl<IUserprofile['departmentId']>;
  divisionId: FormControl<IUserprofile['divisionId']>;
  userStatus: FormControl<IUserprofile['userStatus']>;
};

export type UserprofileFormGroup = FormGroup<UserprofileFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class UserprofileFormService {
  createUserprofileFormGroup(userprofile: UserprofileFormGroupInput = { id: null }): UserprofileFormGroup {
    const userprofileRawValue = {
      ...this.getFormDefaults(),
      ...userprofile,
    };
    return new FormGroup<UserprofileFormGroupContent>({
      id: new FormControl(
        { value: userprofileRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      regionId: new FormControl(userprofileRawValue.regionId),
      branchId: new FormControl(userprofileRawValue.branchId),
      departmentId: new FormControl(userprofileRawValue.departmentId),
      divisionId: new FormControl(userprofileRawValue.divisionId),
      userStatus: new FormControl(userprofileRawValue.userStatus),
    });
  }

  getUserprofile(form: UserprofileFormGroup): IUserprofile | NewUserprofile {
    return form.getRawValue() as IUserprofile | NewUserprofile;
  }

  resetForm(form: UserprofileFormGroup, userprofile: UserprofileFormGroupInput): void {
    const userprofileRawValue = { ...this.getFormDefaults(), ...userprofile };
    form.reset(
      {
        ...userprofileRawValue,
        id: { value: userprofileRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): UserprofileFormDefaults {
    return {
      id: null,
    };
  }
}
