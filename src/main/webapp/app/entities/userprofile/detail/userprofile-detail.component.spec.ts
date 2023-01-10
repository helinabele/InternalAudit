import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { UserprofileDetailComponent } from './userprofile-detail.component';

describe('Userprofile Management Detail Component', () => {
  let comp: UserprofileDetailComponent;
  let fixture: ComponentFixture<UserprofileDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserprofileDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ userprofile: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(UserprofileDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(UserprofileDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load userprofile on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.userprofile).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
