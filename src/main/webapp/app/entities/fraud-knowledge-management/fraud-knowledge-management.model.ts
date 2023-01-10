import dayjs from 'dayjs/esm';

export interface IFraudKnowledgeManagement {
  id: string;
  reportNumber?: number | null;
  caseTitle?: string | null;
  fraudType?: string | null;
  unit?: string | null;
  incidentDate?: dayjs.Dayjs | null;
  reportDate?: dayjs.Dayjs | null;
  internalEmployee?: string | null;
  externalCustomer?: string | null;
  financialLossAmount?: number | null;
  causeForAnIncident?: string | null;
  effect?: string | null;
  recommendationsDrawn?: string | null;
  positionJG?: string | null;
  nameIdNo?: string | null;
  actionInvolved?: string | null;
  ngScreenerReport?: string | null;
  statusAfterReporting?: string | null;
}

export type NewFraudKnowledgeManagement = Omit<IFraudKnowledgeManagement, 'id'> & { id: null };
