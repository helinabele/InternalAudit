import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { UserprofileService } from '../service/userprofile.service';

import { UserprofileComponent } from './userprofile.component';

describe('Userprofile Management Component', () => {
  let comp: UserprofileComponent;
  let fixture: ComponentFixture<UserprofileComponent>;
  let service: UserprofileService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'userprofile', component: UserprofileComponent }]), HttpClientTestingModule],
      declarations: [UserprofileComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            data: of({
              defaultSort: 'id,asc',
            }),
            queryParamMap: of(
              jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              })
            ),
            snapshot: { queryParams: {} },
          },
        },
      ],
    })
      .overrideTemplate(UserprofileComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(UserprofileComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(UserprofileService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 'ABC' }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.userprofiles?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to userprofileService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getUserprofileIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getUserprofileIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
