import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../userprofile.test-samples';

import { UserprofileFormService } from './userprofile-form.service';

describe('Userprofile Form Service', () => {
  let service: UserprofileFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserprofileFormService);
  });

  describe('Service methods', () => {
    describe('createUserprofileFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createUserprofileFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            regionId: expect.any(Object),
            branchId: expect.any(Object),
            departmentId: expect.any(Object),
            divisionId: expect.any(Object),
            userStatus: expect.any(Object),
          })
        );
      });

      it('passing IUserprofile should create a new form with FormGroup', () => {
        const formGroup = service.createUserprofileFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            regionId: expect.any(Object),
            branchId: expect.any(Object),
            departmentId: expect.any(Object),
            divisionId: expect.any(Object),
            userStatus: expect.any(Object),
          })
        );
      });
    });

    describe('getUserprofile', () => {
      it('should return NewUserprofile for default Userprofile initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createUserprofileFormGroup(sampleWithNewData);

        const userprofile = service.getUserprofile(formGroup) as any;

        expect(userprofile).toMatchObject(sampleWithNewData);
      });

      it('should return NewUserprofile for empty Userprofile initial value', () => {
        const formGroup = service.createUserprofileFormGroup();

        const userprofile = service.getUserprofile(formGroup) as any;

        expect(userprofile).toMatchObject({});
      });

      it('should return IUserprofile', () => {
        const formGroup = service.createUserprofileFormGroup(sampleWithRequiredData);

        const userprofile = service.getUserprofile(formGroup) as any;

        expect(userprofile).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IUserprofile should not enable id FormControl', () => {
        const formGroup = service.createUserprofileFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewUserprofile should disable id FormControl', () => {
        const formGroup = service.createUserprofileFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
