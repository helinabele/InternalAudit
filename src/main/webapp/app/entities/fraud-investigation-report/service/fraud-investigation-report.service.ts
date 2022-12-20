import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IFraudInvestigationReport, NewFraudInvestigationReport } from '../fraud-investigation-report.model';

export type PartialUpdateFraudInvestigationReport = Partial<IFraudInvestigationReport> & Pick<IFraudInvestigationReport, 'id'>;

export type EntityResponseType = HttpResponse<IFraudInvestigationReport>;
export type EntityArrayResponseType = HttpResponse<IFraudInvestigationReport[]>;

@Injectable({ providedIn: 'root' })
export class FraudInvestigationReportService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/fraud-investigation-reports');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(fraudInvestigationReport: NewFraudInvestigationReport): Observable<EntityResponseType> {
    return this.http.post<IFraudInvestigationReport>(this.resourceUrl, fraudInvestigationReport, { observe: 'response' });
  }

  update(fraudInvestigationReport: IFraudInvestigationReport): Observable<EntityResponseType> {
    return this.http.put<IFraudInvestigationReport>(
      `${this.resourceUrl}/${this.getFraudInvestigationReportIdentifier(fraudInvestigationReport)}`,
      fraudInvestigationReport,
      { observe: 'response' }
    );
  }

  partialUpdate(fraudInvestigationReport: PartialUpdateFraudInvestigationReport): Observable<EntityResponseType> {
    return this.http.patch<IFraudInvestigationReport>(
      `${this.resourceUrl}/${this.getFraudInvestigationReportIdentifier(fraudInvestigationReport)}`,
      fraudInvestigationReport,
      { observe: 'response' }
    );
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IFraudInvestigationReport>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFraudInvestigationReport[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getFraudInvestigationReportIdentifier(fraudInvestigationReport: Pick<IFraudInvestigationReport, 'id'>): string {
    return fraudInvestigationReport.id;
  }

  compareFraudInvestigationReport(
    o1: Pick<IFraudInvestigationReport, 'id'> | null,
    o2: Pick<IFraudInvestigationReport, 'id'> | null
  ): boolean {
    return o1 && o2 ? this.getFraudInvestigationReportIdentifier(o1) === this.getFraudInvestigationReportIdentifier(o2) : o1 === o2;
  }

  addFraudInvestigationReportToCollectionIfMissing<Type extends Pick<IFraudInvestigationReport, 'id'>>(
    fraudInvestigationReportCollection: Type[],
    ...fraudInvestigationReportsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const fraudInvestigationReports: Type[] = fraudInvestigationReportsToCheck.filter(isPresent);
    if (fraudInvestigationReports.length > 0) {
      const fraudInvestigationReportCollectionIdentifiers = fraudInvestigationReportCollection.map(
        fraudInvestigationReportItem => this.getFraudInvestigationReportIdentifier(fraudInvestigationReportItem)!
      );
      const fraudInvestigationReportsToAdd = fraudInvestigationReports.filter(fraudInvestigationReportItem => {
        const fraudInvestigationReportIdentifier = this.getFraudInvestigationReportIdentifier(fraudInvestigationReportItem);
        if (fraudInvestigationReportCollectionIdentifiers.includes(fraudInvestigationReportIdentifier)) {
          return false;
        }
        fraudInvestigationReportCollectionIdentifiers.push(fraudInvestigationReportIdentifier);
        return true;
      });
      return [...fraudInvestigationReportsToAdd, ...fraudInvestigationReportCollection];
    }
    return fraudInvestigationReportCollection;
  }
}
