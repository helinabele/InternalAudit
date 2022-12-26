import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IAssignTask, NewAssignTask } from '../assign-task.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAssignTask for edit and NewAssignTaskFormGroupInput for create.
 */
type AssignTaskFormGroupInput = IAssignTask | PartialWithRequiredKeyOf<NewAssignTask>;

type AssignTaskFormDefaults = Pick<NewAssignTask, 'id'>;

type AssignTaskFormGroupContent = {
  id: FormControl<IAssignTask['id'] | NewAssignTask['id']>;
  title: FormControl<IAssignTask['title']>;
  assignedPerson: FormControl<IAssignTask['assignedPerson']>;
  taskAssignmentDate: FormControl<IAssignTask['taskAssignmentDate']>;
  taskStartDate: FormControl<IAssignTask['taskStartDate']>;
  taskDueDate: FormControl<IAssignTask['taskDueDate']>;
};

export type AssignTaskFormGroup = FormGroup<AssignTaskFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AssignTaskFormService {
  createAssignTaskFormGroup(assignTask: AssignTaskFormGroupInput = { id: null }): AssignTaskFormGroup {
    const assignTaskRawValue = {
      ...this.getFormDefaults(),
      ...assignTask,
    };
    return new FormGroup<AssignTaskFormGroupContent>({
      id: new FormControl(
        { value: assignTaskRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      title: new FormControl(assignTaskRawValue.title, {
        validators: [Validators.required],
      }),
      assignedPerson: new FormControl(assignTaskRawValue.assignedPerson),
      taskAssignmentDate: new FormControl(assignTaskRawValue.taskAssignmentDate),
      taskStartDate: new FormControl(assignTaskRawValue.taskStartDate),
      taskDueDate: new FormControl(assignTaskRawValue.taskDueDate),
    });
  }

  getAssignTask(form: AssignTaskFormGroup): IAssignTask | NewAssignTask {
    return form.getRawValue() as IAssignTask | NewAssignTask;
  }

  resetForm(form: AssignTaskFormGroup, assignTask: AssignTaskFormGroupInput): void {
    const assignTaskRawValue = { ...this.getFormDefaults(), ...assignTask };
    form.reset(
      {
        ...assignTaskRawValue,
        id: { value: assignTaskRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): AssignTaskFormDefaults {
    return {
      id: null,
    };
  }
}
