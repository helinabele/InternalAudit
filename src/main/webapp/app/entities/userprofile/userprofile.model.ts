import { UserStatus } from 'app/entities/enumerations/user-status.model';

export interface IUserprofile {
  id: string;
  regionId?: number | null;
  branchId?: number | null;
  departmentId?: number | null;
  divisionId?: number | null;
  userStatus?: UserStatus | null;
}

export type NewUserprofile = Omit<IUserprofile, 'id'> & { id: null };
