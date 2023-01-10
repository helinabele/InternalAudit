import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { UserprofileComponent } from '../list/userprofile.component';
import { UserprofileDetailComponent } from '../detail/userprofile-detail.component';
import { UserprofileUpdateComponent } from '../update/userprofile-update.component';
import { UserprofileRoutingResolveService } from './userprofile-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const userprofileRoute: Routes = [
  {
    path: '',
    component: UserprofileComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: UserprofileDetailComponent,
    resolve: {
      userprofile: UserprofileRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: UserprofileUpdateComponent,
    resolve: {
      userprofile: UserprofileRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: UserprofileUpdateComponent,
    resolve: {
      userprofile: UserprofileRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(userprofileRoute)],
  exports: [RouterModule],
})
export class UserprofileRoutingModule {}
