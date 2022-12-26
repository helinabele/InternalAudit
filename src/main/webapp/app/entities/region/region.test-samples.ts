import { IRegion, NewRegion } from './region.model';

export const sampleWithRequiredData: IRegion = {
  id: '4ac22173-a27f-4cc2-bff5-851da2a397e4',
};

export const sampleWithPartialData: IRegion = {
  id: 'ce1af650-7736-4459-8ced-374684f19133',
};

export const sampleWithFullData: IRegion = {
  id: '19f7aa23-864e-4b52-9d31-1f32b2c8cab4',
  regionName: 'SMTP Swiss Licensed',
  regionId: 60634,
  description: 'Mississippi Industrial Corporate',
};

export const sampleWithNewData: NewRegion = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
