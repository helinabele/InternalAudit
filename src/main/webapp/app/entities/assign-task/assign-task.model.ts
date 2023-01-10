import dayjs from 'dayjs/esm';

export interface IAssignTask {
  id: string;
  title?: string | null;
  assignedPerson?: string | null;
  taskAssignmentDate?: dayjs.Dayjs | null;
  taskStartDate?: dayjs.Dayjs | null;
  taskDueDate?: dayjs.Dayjs | null;
}

export type NewAssignTask = Omit<IAssignTask, 'id'> & { id: null };
