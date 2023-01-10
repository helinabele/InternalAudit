import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IFraudInvestigationReport, NewFraudInvestigationReport } from '../fraud-investigation-report.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFraudInvestigationReport for edit and NewFraudInvestigationReportFormGroupInput for create.
 */
type FraudInvestigationReportFormGroupInput = IFraudInvestigationReport | PartialWithRequiredKeyOf<NewFraudInvestigationReport>;

type FraudInvestigationReportFormDefaults = Pick<NewFraudInvestigationReport, 'id'>;

type FraudInvestigationReportFormGroupContent = {
  id: FormControl<IFraudInvestigationReport['id'] | NewFraudInvestigationReport['id']>;
  title: FormControl<IFraudInvestigationReport['title']>;
  executiveSummary: FormControl<IFraudInvestigationReport['executiveSummary']>;
  introduction: FormControl<IFraudInvestigationReport['introduction']>;
  objective: FormControl<IFraudInvestigationReport['objective']>;
  scope: FormControl<IFraudInvestigationReport['scope']>;
  limitation: FormControl<IFraudInvestigationReport['limitation']>;
  methodology: FormControl<IFraudInvestigationReport['methodology']>;
  findingAndAnalysis: FormControl<IFraudInvestigationReport['findingAndAnalysis']>;
  conclusion: FormControl<IFraudInvestigationReport['conclusion']>;
  recommendation: FormControl<IFraudInvestigationReport['recommendation']>;
  nameOfMembers: FormControl<IFraudInvestigationReport['nameOfMembers']>;
  signature: FormControl<IFraudInvestigationReport['signature']>;
  annexes: FormControl<IFraudInvestigationReport['annexes']>;
  author: FormControl<IFraudInvestigationReport['author']>;
  fraudType: FormControl<IFraudInvestigationReport['fraudType']>;
};

export type FraudInvestigationReportFormGroup = FormGroup<FraudInvestigationReportFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class FraudInvestigationReportFormService {
  createFraudInvestigationReportFormGroup(
    fraudInvestigationReport: FraudInvestigationReportFormGroupInput = { id: null }
  ): FraudInvestigationReportFormGroup {
    const fraudInvestigationReportRawValue = {
      ...this.getFormDefaults(),
      ...fraudInvestigationReport,
    };
    return new FormGroup<FraudInvestigationReportFormGroupContent>({
      id: new FormControl(
        { value: fraudInvestigationReportRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      title: new FormControl(fraudInvestigationReportRawValue.title),
      executiveSummary: new FormControl(fraudInvestigationReportRawValue.executiveSummary),
      introduction: new FormControl(fraudInvestigationReportRawValue.introduction),
      objective: new FormControl(fraudInvestigationReportRawValue.objective),
      scope: new FormControl(fraudInvestigationReportRawValue.scope),
      limitation: new FormControl(fraudInvestigationReportRawValue.limitation),
      methodology: new FormControl(fraudInvestigationReportRawValue.methodology),
      findingAndAnalysis: new FormControl(fraudInvestigationReportRawValue.findingAndAnalysis),
      conclusion: new FormControl(fraudInvestigationReportRawValue.conclusion),
      recommendation: new FormControl(fraudInvestigationReportRawValue.recommendation),
      nameOfMembers: new FormControl(fraudInvestigationReportRawValue.nameOfMembers),
      signature: new FormControl(fraudInvestigationReportRawValue.signature),
      annexes: new FormControl(fraudInvestigationReportRawValue.annexes),
      author: new FormControl(fraudInvestigationReportRawValue.author),
      fraudType: new FormControl(fraudInvestigationReportRawValue.fraudType),
    });
  }

  getFraudInvestigationReport(form: FraudInvestigationReportFormGroup): IFraudInvestigationReport | NewFraudInvestigationReport {
    return form.getRawValue() as IFraudInvestigationReport | NewFraudInvestigationReport;
  }

  resetForm(form: FraudInvestigationReportFormGroup, fraudInvestigationReport: FraudInvestigationReportFormGroupInput): void {
    const fraudInvestigationReportRawValue = { ...this.getFormDefaults(), ...fraudInvestigationReport };
    form.reset(
      {
        ...fraudInvestigationReportRawValue,
        id: { value: fraudInvestigationReportRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): FraudInvestigationReportFormDefaults {
    return {
      id: null,
    };
  }
}
