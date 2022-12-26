import dayjs from 'dayjs/esm';

import { IAssignTask, NewAssignTask } from './assign-task.model';

export const sampleWithRequiredData: IAssignTask = {
  id: '1f3a18e1-b68a-49fe-bba8-5ae63612b2cd',
  title: 'synthesizing Somali Gloves',
};

export const sampleWithPartialData: IAssignTask = {
  id: '872c73de-9c19-4168-a9cb-20fde2034b40',
  title: 'proactive Avon override',
  taskAssignmentDate: dayjs('2022-12-25'),
};

export const sampleWithFullData: IAssignTask = {
  id: 'eaf5dbd3-1e04-4668-b061-cd16fd85c5bf',
  title: 'upward-trending',
  assignedPerson: 'port',
  taskAssignmentDate: dayjs('2022-12-26'),
  taskStartDate: dayjs('2022-12-26'),
  taskDueDate: dayjs('2022-12-25'),
};

export const sampleWithNewData: NewAssignTask = {
  title: 'Mouse',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
