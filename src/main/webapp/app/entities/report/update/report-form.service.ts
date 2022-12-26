import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IReport, NewReport } from '../report.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IReport for edit and NewReportFormGroupInput for create.
 */
type ReportFormGroupInput = IReport | PartialWithRequiredKeyOf<NewReport>;

type ReportFormDefaults = Pick<NewReport, 'id'>;

type ReportFormGroupContent = {
  id: FormControl<IReport['id'] | NewReport['id']>;
  title: FormControl<IReport['title']>;
  executiveSummary: FormControl<IReport['executiveSummary']>;
  introduction: FormControl<IReport['introduction']>;
  objective: FormControl<IReport['objective']>;
  scope: FormControl<IReport['scope']>;
  limitation: FormControl<IReport['limitation']>;
  methodology: FormControl<IReport['methodology']>;
  findingAndAnalysis: FormControl<IReport['findingAndAnalysis']>;
  conclusion: FormControl<IReport['conclusion']>;
  recommendation: FormControl<IReport['recommendation']>;
  nameOfMembers: FormControl<IReport['nameOfMembers']>;
  signature: FormControl<IReport['signature']>;
  annexes: FormControl<IReport['annexes']>;
  author: FormControl<IReport['author']>;
};

export type ReportFormGroup = FormGroup<ReportFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ReportFormService {
  createReportFormGroup(report: ReportFormGroupInput = { id: null }): ReportFormGroup {
    const reportRawValue = {
      ...this.getFormDefaults(),
      ...report,
    };
    return new FormGroup<ReportFormGroupContent>({
      id: new FormControl(
        { value: reportRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      title: new FormControl(reportRawValue.title),
      executiveSummary: new FormControl(reportRawValue.executiveSummary),
      introduction: new FormControl(reportRawValue.introduction),
      objective: new FormControl(reportRawValue.objective),
      scope: new FormControl(reportRawValue.scope),
      limitation: new FormControl(reportRawValue.limitation),
      methodology: new FormControl(reportRawValue.methodology),
      findingAndAnalysis: new FormControl(reportRawValue.findingAndAnalysis),
      conclusion: new FormControl(reportRawValue.conclusion),
      recommendation: new FormControl(reportRawValue.recommendation),
      nameOfMembers: new FormControl(reportRawValue.nameOfMembers),
      signature: new FormControl(reportRawValue.signature),
      annexes: new FormControl(reportRawValue.annexes),
      author: new FormControl(reportRawValue.author),
    });
  }

  getReport(form: ReportFormGroup): IReport | NewReport {
    return form.getRawValue() as IReport | NewReport;
  }

  resetForm(form: ReportFormGroup, report: ReportFormGroupInput): void {
    const reportRawValue = { ...this.getFormDefaults(), ...report };
    form.reset(
      {
        ...reportRawValue,
        id: { value: reportRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): ReportFormDefaults {
    return {
      id: null,
    };
  }
}
