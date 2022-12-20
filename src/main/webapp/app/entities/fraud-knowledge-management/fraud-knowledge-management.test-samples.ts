import dayjs from 'dayjs/esm';

import { IFraudKnowledgeManagement, NewFraudKnowledgeManagement } from './fraud-knowledge-management.model';

export const sampleWithRequiredData: IFraudKnowledgeManagement = {
  id: '6d9aadea-b2f2-48ba-a7fc-ef5e0f0e87b9',
};

export const sampleWithPartialData: IFraudKnowledgeManagement = {
  id: '859d32f9-3d70-4505-838e-94ae7eda69e0',
  fraudType: 'out-of-the-box web-enabled Jordanian',
  incidentDate: dayjs('2022-12-16'),
  reportDate: dayjs('2022-12-15'),
  externalCustomer: 'implementation Baht',
  financialLossAmount: 8983,
  causeForAnIncident: 'models synthesize',
  actionInvolved: 'Table Centralized payment',
  ngScreenerReport: 'China Metical',
  statusAfterReporting: 'Visionary',
};

export const sampleWithFullData: IFraudKnowledgeManagement = {
  id: '28b15104-fd85-41fd-9f61-9f3419f2bd53',
  reportNumber: 6540,
  caseTitle: 'Technician',
  fraudType: 'payment',
  unit: 'Card COM Concrete',
  incidentDate: dayjs('2022-12-15'),
  reportDate: dayjs('2022-12-15'),
  internalEmployee: 'e-commerce e-enable Senior',
  externalCustomer: 'overriding South Garden',
  financialLossAmount: 76980,
  causeForAnIncident: 'hacking Tools architecture',
  effect: 'Tools',
  recommendationsDrawn: 'Greece',
  positionJG: 'Infrastructure COM seize',
  nameIdNo: 'Kwacha visualize Sleek',
  actionInvolved: 'deposit',
  ngScreenerReport: 'Refined Money azure',
  statusAfterReporting: 'value-added programming Refined',
};

export const sampleWithNewData: NewFraudKnowledgeManagement = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
