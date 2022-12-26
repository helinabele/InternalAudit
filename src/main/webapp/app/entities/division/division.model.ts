import { IUserprofile } from 'app/entities/userprofile/userprofile.model';
import { IDepartment } from 'app/entities/department/department.model';

export interface IDivision {
  id: string;
  description?: string | null;
  divisionId?: number | null;
  divisionName?: string | null;
  userproifle?: Pick<IUserprofile, 'id' | 'divisionId'> | null;
  department?: Pick<IDepartment, 'id'> | null;
}

export type NewDivision = Omit<IDivision, 'id'> & { id: null };
