import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../assign-task.test-samples';

import { AssignTaskFormService } from './assign-task-form.service';

describe('AssignTask Form Service', () => {
  let service: AssignTaskFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AssignTaskFormService);
  });

  describe('Service methods', () => {
    describe('createAssignTaskFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAssignTaskFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            title: expect.any(Object),
            assignedPerson: expect.any(Object),
            taskAssignmentDate: expect.any(Object),
            taskStartDate: expect.any(Object),
            taskDueDate: expect.any(Object),
          })
        );
      });

      it('passing IAssignTask should create a new form with FormGroup', () => {
        const formGroup = service.createAssignTaskFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            title: expect.any(Object),
            assignedPerson: expect.any(Object),
            taskAssignmentDate: expect.any(Object),
            taskStartDate: expect.any(Object),
            taskDueDate: expect.any(Object),
          })
        );
      });
    });

    describe('getAssignTask', () => {
      it('should return NewAssignTask for default AssignTask initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createAssignTaskFormGroup(sampleWithNewData);

        const assignTask = service.getAssignTask(formGroup) as any;

        expect(assignTask).toMatchObject(sampleWithNewData);
      });

      it('should return NewAssignTask for empty AssignTask initial value', () => {
        const formGroup = service.createAssignTaskFormGroup();

        const assignTask = service.getAssignTask(formGroup) as any;

        expect(assignTask).toMatchObject({});
      });

      it('should return IAssignTask', () => {
        const formGroup = service.createAssignTaskFormGroup(sampleWithRequiredData);

        const assignTask = service.getAssignTask(formGroup) as any;

        expect(assignTask).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAssignTask should not enable id FormControl', () => {
        const formGroup = service.createAssignTaskFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAssignTask should disable id FormControl', () => {
        const formGroup = service.createAssignTaskFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
