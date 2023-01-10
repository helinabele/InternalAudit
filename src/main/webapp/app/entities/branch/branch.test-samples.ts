import { IBranch, NewBranch } from './branch.model';

export const sampleWithRequiredData: IBranch = {
  id: '0c38fc2c-0367-4497-964e-31bc165ed1a5',
};

export const sampleWithPartialData: IBranch = {
  id: '3eeff70a-25cb-4f6e-a083-c6b2a87a85c5',
  branchName: 'Colorado Sum world-class',
  branchId: 95256,
  description: 'responsive',
};

export const sampleWithFullData: IBranch = {
  id: 'd20f5386-5afb-4b1e-8763-99237af921fd',
  branchName: 'International',
  branchId: 9340,
  description: 'payment Handmade lavender',
};

export const sampleWithNewData: NewBranch = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
