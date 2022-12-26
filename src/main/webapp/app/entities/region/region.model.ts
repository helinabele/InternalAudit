import { IUserprofile } from 'app/entities/userprofile/userprofile.model';

export interface IRegion {
  id: string;
  regionName?: string | null;
  regionId?: number | null;
  description?: string | null;
  userproifle?: Pick<IUserprofile, 'id' | 'regionId'> | null;
}

export type NewRegion = Omit<IRegion, 'id'> & { id: null };
