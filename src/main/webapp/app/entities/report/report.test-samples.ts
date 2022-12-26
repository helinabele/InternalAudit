import { IReport, NewReport } from './report.model';

export const sampleWithRequiredData: IReport = {
  id: '5eb0187d-5aa9-4c43-9c5d-491d2b5289ac',
};

export const sampleWithPartialData: IReport = {
  id: '02257ecf-ad4f-48a1-8d25-5af8251c86fc',
  executiveSummary: 'Enhanced',
  objective: 'Representative Account',
  methodology: 'Switchable',
  nameOfMembers: 'Cambridgeshire evolve HTTP',
  signature: 'Market systems neutral',
  annexes: 'functionalities invoice',
};

export const sampleWithFullData: IReport = {
  id: '01b46d47-0c6d-4c77-b551-1d6776b8a464',
  title: 'withdrawal',
  executiveSummary: 'indexing sky',
  introduction: 'solid Facilitator',
  objective: 'innovative',
  scope: 'driver Dynamic',
  limitation: 'application Burundi',
  methodology: 'capacitor Optimization',
  findingAndAnalysis: 'SCSI',
  conclusion: 'Movies',
  recommendation: 'edge aggregate',
  nameOfMembers: 'GB Lanka capacitor',
  signature: 'Baby Kids transmitting',
  annexes: 'impactful',
  author: 'withdrawal Yuan',
};

export const sampleWithNewData: NewReport = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
