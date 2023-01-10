import { IFraudKnowledgeManagement } from 'app/entities/fraud-knowledge-management/fraud-knowledge-management.model';

export interface IFraudInvestigationReport {
  id: string;
  title?: string | null;
  executiveSummary?: string | null;
  introduction?: string | null;
  objective?: string | null;
  scope?: string | null;
  limitation?: string | null;
  methodology?: string | null;
  findingAndAnalysis?: string | null;
  conclusion?: string | null;
  recommendation?: string | null;
  nameOfMembers?: string | null;
  signature?: string | null;
  annexes?: string | null;
  author?: string | null;
  fraudType?: Pick<IFraudKnowledgeManagement, 'id'> | null;
}

export type NewFraudInvestigationReport = Omit<IFraudInvestigationReport, 'id'> & { id: null };
