import { IDivision, NewDivision } from './division.model';

export const sampleWithRequiredData: IDivision = {
  id: '753eab59-e38e-452f-8803-50c1401dc97e',
};

export const sampleWithPartialData: IDivision = {
  id: 'b2274887-e035-44d7-a1c9-3d711a6018d0',
  description: 'Usability',
};

export const sampleWithFullData: IDivision = {
  id: 'b9ffbcad-7fe2-4397-b5ef-44e507546370',
  description: 'collaborative',
  divisionId: 32306,
  divisionName: 'De-engineered demand-driven',
};

export const sampleWithNewData: NewDivision = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
