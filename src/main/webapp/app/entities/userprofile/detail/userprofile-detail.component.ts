import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserprofile } from '../userprofile.model';

@Component({
  selector: 'jhi-userprofile-detail',
  templateUrl: './userprofile-detail.component.html',
})
export class UserprofileDetailComponent implements OnInit {
  userprofile: IUserprofile | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userprofile }) => {
      this.userprofile = userprofile;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
