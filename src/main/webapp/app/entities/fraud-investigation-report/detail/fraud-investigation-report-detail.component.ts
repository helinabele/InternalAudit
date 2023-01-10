import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFraudInvestigationReport } from '../fraud-investigation-report.model';

@Component({
  selector: 'jhi-fraud-investigation-report-detail',
  templateUrl: './fraud-investigation-report-detail.component.html',
})
export class FraudInvestigationReportDetailComponent implements OnInit {
  fraudInvestigationReport: IFraudInvestigationReport | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fraudInvestigationReport }) => {
      this.fraudInvestigationReport = fraudInvestigationReport;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
