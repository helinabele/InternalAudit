import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IUserprofile } from '../userprofile.model';
import { UserprofileService } from '../service/userprofile.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './userprofile-delete-dialog.component.html',
})
export class UserprofileDeleteDialogComponent {
  userprofile?: IUserprofile;

  constructor(protected userprofileService: UserprofileService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.userprofileService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
