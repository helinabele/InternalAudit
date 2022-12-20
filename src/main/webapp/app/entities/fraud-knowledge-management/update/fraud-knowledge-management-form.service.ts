import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IFraudKnowledgeManagement, NewFraudKnowledgeManagement } from '../fraud-knowledge-management.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFraudKnowledgeManagement for edit and NewFraudKnowledgeManagementFormGroupInput for create.
 */
type FraudKnowledgeManagementFormGroupInput = IFraudKnowledgeManagement | PartialWithRequiredKeyOf<NewFraudKnowledgeManagement>;

type FraudKnowledgeManagementFormDefaults = Pick<NewFraudKnowledgeManagement, 'id'>;

type FraudKnowledgeManagementFormGroupContent = {
  id: FormControl<IFraudKnowledgeManagement['id'] | NewFraudKnowledgeManagement['id']>;
  reportNumber: FormControl<IFraudKnowledgeManagement['reportNumber']>;
  caseTitle: FormControl<IFraudKnowledgeManagement['caseTitle']>;
  fraudType: FormControl<IFraudKnowledgeManagement['fraudType']>;
  unit: FormControl<IFraudKnowledgeManagement['unit']>;
  incidentDate: FormControl<IFraudKnowledgeManagement['incidentDate']>;
  reportDate: FormControl<IFraudKnowledgeManagement['reportDate']>;
  internalEmployee: FormControl<IFraudKnowledgeManagement['internalEmployee']>;
  externalCustomer: FormControl<IFraudKnowledgeManagement['externalCustomer']>;
  financialLossAmount: FormControl<IFraudKnowledgeManagement['financialLossAmount']>;
  causeForAnIncident: FormControl<IFraudKnowledgeManagement['causeForAnIncident']>;
  effect: FormControl<IFraudKnowledgeManagement['effect']>;
  recommendationsDrawn: FormControl<IFraudKnowledgeManagement['recommendationsDrawn']>;
  positionJG: FormControl<IFraudKnowledgeManagement['positionJG']>;
  nameIdNo: FormControl<IFraudKnowledgeManagement['nameIdNo']>;
  actionInvolved: FormControl<IFraudKnowledgeManagement['actionInvolved']>;
  ngScreenerReport: FormControl<IFraudKnowledgeManagement['ngScreenerReport']>;
  statusAfterReporting: FormControl<IFraudKnowledgeManagement['statusAfterReporting']>;
};

export type FraudKnowledgeManagementFormGroup = FormGroup<FraudKnowledgeManagementFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class FraudKnowledgeManagementFormService {
  createFraudKnowledgeManagementFormGroup(
    fraudKnowledgeManagement: FraudKnowledgeManagementFormGroupInput = { id: null }
  ): FraudKnowledgeManagementFormGroup {
    const fraudKnowledgeManagementRawValue = {
      ...this.getFormDefaults(),
      ...fraudKnowledgeManagement,
    };
    return new FormGroup<FraudKnowledgeManagementFormGroupContent>({
      id: new FormControl(
        { value: fraudKnowledgeManagementRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      reportNumber: new FormControl(fraudKnowledgeManagementRawValue.reportNumber),
      caseTitle: new FormControl(fraudKnowledgeManagementRawValue.caseTitle),
      fraudType: new FormControl(fraudKnowledgeManagementRawValue.fraudType),
      unit: new FormControl(fraudKnowledgeManagementRawValue.unit),
      incidentDate: new FormControl(fraudKnowledgeManagementRawValue.incidentDate),
      reportDate: new FormControl(fraudKnowledgeManagementRawValue.reportDate),
      internalEmployee: new FormControl(fraudKnowledgeManagementRawValue.internalEmployee),
      externalCustomer: new FormControl(fraudKnowledgeManagementRawValue.externalCustomer),
      financialLossAmount: new FormControl(fraudKnowledgeManagementRawValue.financialLossAmount),
      causeForAnIncident: new FormControl(fraudKnowledgeManagementRawValue.causeForAnIncident),
      effect: new FormControl(fraudKnowledgeManagementRawValue.effect),
      recommendationsDrawn: new FormControl(fraudKnowledgeManagementRawValue.recommendationsDrawn),
      positionJG: new FormControl(fraudKnowledgeManagementRawValue.positionJG),
      nameIdNo: new FormControl(fraudKnowledgeManagementRawValue.nameIdNo),
      actionInvolved: new FormControl(fraudKnowledgeManagementRawValue.actionInvolved),
      ngScreenerReport: new FormControl(fraudKnowledgeManagementRawValue.ngScreenerReport),
      statusAfterReporting: new FormControl(fraudKnowledgeManagementRawValue.statusAfterReporting),
    });
  }

  getFraudKnowledgeManagement(form: FraudKnowledgeManagementFormGroup): IFraudKnowledgeManagement | NewFraudKnowledgeManagement {
    return form.getRawValue() as IFraudKnowledgeManagement | NewFraudKnowledgeManagement;
  }

  resetForm(form: FraudKnowledgeManagementFormGroup, fraudKnowledgeManagement: FraudKnowledgeManagementFormGroupInput): void {
    const fraudKnowledgeManagementRawValue = { ...this.getFormDefaults(), ...fraudKnowledgeManagement };
    form.reset(
      {
        ...fraudKnowledgeManagementRawValue,
        id: { value: fraudKnowledgeManagementRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): FraudKnowledgeManagementFormDefaults {
    return {
      id: null,
    };
  }
}
