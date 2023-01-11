import dayjs from 'dayjs/esm';
import { ITask } from '../task/task.model';

export interface IAssignTask {
  id: string;
  title?: string | null;
  assignedPerson?: string | null;
  taskAssignmentDate?: dayjs.Dayjs | null;
  taskStartDate?: dayjs.Dayjs | null;
  taskDueDate?: dayjs.Dayjs | null;
  tasks?: ITask[];
}

export type NewAssignTask = Omit<IAssignTask, 'id'> & { id: null };
