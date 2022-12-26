import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { UserprofileComponent } from './list/userprofile.component';
import { UserprofileDetailComponent } from './detail/userprofile-detail.component';
import { UserprofileUpdateComponent } from './update/userprofile-update.component';
import { UserprofileDeleteDialogComponent } from './delete/userprofile-delete-dialog.component';
import { UserprofileRoutingModule } from './route/userprofile-routing.module';

@NgModule({
  imports: [SharedModule, UserprofileRoutingModule],
  declarations: [UserprofileComponent, UserprofileDetailComponent, UserprofileUpdateComponent, UserprofileDeleteDialogComponent],
})
export class UserprofileModule {}
