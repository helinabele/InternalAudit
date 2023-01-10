import { IFraudInvestigationReport, NewFraudInvestigationReport } from './fraud-investigation-report.model';

export const sampleWithRequiredData: IFraudInvestigationReport = {
  id: '785f0698-481f-4d76-9cd2-cbb48dba68c1',
};

export const sampleWithPartialData: IFraudInvestigationReport = {
  id: 'fcaa73a2-5ca2-41b8-ab44-91c0d234b044',
  title: 'XSS New reboot',
  findingAndAnalysis: 'Bedfordshire FTP',
  conclusion: '1080p New olive',
  nameOfMembers: 'override withdrawal Dam',
};

export const sampleWithFullData: IFraudInvestigationReport = {
  id: '01edf9d8-09cd-4a76-af82-f040113bf20c',
  title: 'Maine',
  executiveSummary: 'Bacon 24/7',
  introduction: 'firewall invoice encoding',
  objective: 'Usability Borders Arkansas',
  scope: 'Morocco TCP',
  limitation: 'maximize Cambridgeshire',
  methodology: 'impactful scale',
  findingAndAnalysis: 'Berkshire',
  conclusion: 'Central',
  recommendation: 'National Rupee',
  nameOfMembers: 'protocol',
  signature: 'Concrete Keyboard partnerships',
  annexes: 'navigate Executive Incredible',
  author: 'Hat',
};

export const sampleWithNewData: NewFraudInvestigationReport = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
