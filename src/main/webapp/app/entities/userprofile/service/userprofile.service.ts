import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IUserprofile, NewUserprofile } from '../userprofile.model';

export type PartialUpdateUserprofile = Partial<IUserprofile> & Pick<IUserprofile, 'id'>;

export type EntityResponseType = HttpResponse<IUserprofile>;
export type EntityArrayResponseType = HttpResponse<IUserprofile[]>;

@Injectable({ providedIn: 'root' })
export class UserprofileService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/userprofiles');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(userprofile: NewUserprofile): Observable<EntityResponseType> {
    return this.http.post<IUserprofile>(this.resourceUrl, userprofile, { observe: 'response' });
  }

  update(userprofile: IUserprofile): Observable<EntityResponseType> {
    return this.http.put<IUserprofile>(`${this.resourceUrl}/${this.getUserprofileIdentifier(userprofile)}`, userprofile, {
      observe: 'response',
    });
  }

  partialUpdate(userprofile: PartialUpdateUserprofile): Observable<EntityResponseType> {
    return this.http.patch<IUserprofile>(`${this.resourceUrl}/${this.getUserprofileIdentifier(userprofile)}`, userprofile, {
      observe: 'response',
    });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IUserprofile>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUserprofile[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getUserprofileIdentifier(userprofile: Pick<IUserprofile, 'id'>): string {
    return userprofile.id;
  }

  compareUserprofile(o1: Pick<IUserprofile, 'id'> | null, o2: Pick<IUserprofile, 'id'> | null): boolean {
    return o1 && o2 ? this.getUserprofileIdentifier(o1) === this.getUserprofileIdentifier(o2) : o1 === o2;
  }

  addUserprofileToCollectionIfMissing<Type extends Pick<IUserprofile, 'id'>>(
    userprofileCollection: Type[],
    ...userprofilesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const userprofiles: Type[] = userprofilesToCheck.filter(isPresent);
    if (userprofiles.length > 0) {
      const userprofileCollectionIdentifiers = userprofileCollection.map(
        userprofileItem => this.getUserprofileIdentifier(userprofileItem)!
      );
      const userprofilesToAdd = userprofiles.filter(userprofileItem => {
        const userprofileIdentifier = this.getUserprofileIdentifier(userprofileItem);
        if (userprofileCollectionIdentifiers.includes(userprofileIdentifier)) {
          return false;
        }
        userprofileCollectionIdentifiers.push(userprofileIdentifier);
        return true;
      });
      return [...userprofilesToAdd, ...userprofileCollection];
    }
    return userprofileCollection;
  }
}
