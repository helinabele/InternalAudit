import { IUserprofile } from 'app/entities/userprofile/userprofile.model';
import { IBranch } from 'app/entities/branch/branch.model';

export interface IDepartment {
  id: string;
  departmentName?: string | null;
  departmentId?: number | null;
  description?: string | null;
  userproifle?: Pick<IUserprofile, 'id' | 'departmentId'> | null;
  branch?: Pick<IBranch, 'id'> | null;
}

export type NewDepartment = Omit<IDepartment, 'id'> & { id: null };
