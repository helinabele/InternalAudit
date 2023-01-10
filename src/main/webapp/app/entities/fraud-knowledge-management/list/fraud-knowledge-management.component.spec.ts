import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { FraudKnowledgeManagementService } from '../service/fraud-knowledge-management.service';

import { FraudKnowledgeManagementComponent } from './fraud-knowledge-management.component';

describe('FraudKnowledgeManagement Management Component', () => {
  let comp: FraudKnowledgeManagementComponent;
  let fixture: ComponentFixture<FraudKnowledgeManagementComponent>;
  let service: FraudKnowledgeManagementService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'fraud-knowledge-management', component: FraudKnowledgeManagementComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [FraudKnowledgeManagementComponent],
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
      .overrideTemplate(FraudKnowledgeManagementComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FraudKnowledgeManagementComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(FraudKnowledgeManagementService);

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
    expect(comp.fraudKnowledgeManagements?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to fraudKnowledgeManagementService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getFraudKnowledgeManagementIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getFraudKnowledgeManagementIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
