import { UserStatus } from 'app/entities/enumerations/user-status.model';

import { IUserprofile, NewUserprofile } from './userprofile.model';

export const sampleWithRequiredData: IUserprofile = {
  id: '1397b978-ebca-4258-8567-92f2696e7e1d',
};

export const sampleWithPartialData: IUserprofile = {
  id: 'ec737ef7-0a42-4446-9a97-26f2cfabff31',
  regionId: 86066,
  divisionId: 64989,
  userStatus: UserStatus['AUTUMN'],
};

export const sampleWithFullData: IUserprofile = {
  id: 'b7e66ba0-3d71-4b00-9c16-c8627a5b0e5a',
  regionId: 49733,
  branchId: 29620,
  departmentId: 5061,
  divisionId: 76007,
  userStatus: UserStatus['AUTUMN'],
};

export const sampleWithNewData: NewUserprofile = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
