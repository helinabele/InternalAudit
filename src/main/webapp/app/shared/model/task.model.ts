import { IAssignTask } from 'app/entities/assign-task/assign-task.model';

export interface ITask {
  id: string;
  title?: string | null;
  description?: string | null;
  assignTask?: Pick<IAssignTask, 'id' | 'title'> | null;
}

export type NewTask = Omit<ITask, 'id'> & { id: null };
