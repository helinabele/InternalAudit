import { IDepartment, NewDepartment } from './department.model';

export const sampleWithRequiredData: IDepartment = {
  id: '684b861b-2c15-4402-a277-318933fd4b5b',
  departmentName: 'digital user-centric',
};

export const sampleWithPartialData: IDepartment = {
  id: '6701eb35-f702-4c34-b74f-e4f434215bb7',
  departmentName: 'Shirt Manor',
  departmentId: 19170,
};

export const sampleWithFullData: IDepartment = {
  id: '3fb7e98a-2819-462f-9924-44a0201fd6a5',
  departmentName: 'Toys Gorgeous Minnesota',
  departmentId: 39105,
  description: 'Electronics Up-sized',
};

export const sampleWithNewData: NewDepartment = {
  departmentName: 'bi-directional Borders',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
