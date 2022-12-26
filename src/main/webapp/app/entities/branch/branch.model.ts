import { IUserprofile } from 'app/entities/userprofile/userprofile.model';
import { IRegion } from 'app/entities/region/region.model';

export interface IBranch {
  id: string;
  branchName?: string | null;
  branchId?: number | null;
  description?: string | null;
  userproifle?: Pick<IUserprofile, 'id' | 'branchId'> | null;
  region?: Pick<IRegion, 'id' | 'regionName'> | null;
}

export type NewBranch = Omit<IBranch, 'id'> & { id: null };
