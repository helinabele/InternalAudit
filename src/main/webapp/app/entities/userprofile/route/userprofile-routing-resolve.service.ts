import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IUserprofile } from '../userprofile.model';
import { UserprofileService } from '../service/userprofile.service';

@Injectable({ providedIn: 'root' })
export class UserprofileRoutingResolveService implements Resolve<IUserprofile | null> {
  constructor(protected service: UserprofileService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUserprofile | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((userprofile: HttpResponse<IUserprofile>) => {
          if (userprofile.body) {
            return of(userprofile.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(null);
  }
}
