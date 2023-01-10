import { ITask, NewTask } from './task.model';

export const sampleWithRequiredData: ITask = {
  id: '6cd369ff-3e50-44dc-bff2-85e0c26ef9ed',
  title: "Pa'anga Soft Producer",
};

export const sampleWithPartialData: ITask = {
  id: 'dbac5135-b327-49bc-9af4-bf860d0b49af',
  title: 'Sleek',
  description: 'disintermediate',
};

export const sampleWithFullData: ITask = {
  id: '1810297e-e023-4b64-a2d6-d401a6b32d1d',
  title: 'Strategist Springs white',
  description: 'synergize Berkshire Usability',
};

export const sampleWithNewData: NewTask = {
  title: 'orchid',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
