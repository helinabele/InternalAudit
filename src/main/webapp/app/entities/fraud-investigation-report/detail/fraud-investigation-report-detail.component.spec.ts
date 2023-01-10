import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FraudInvestigationReportDetailComponent } from './fraud-investigation-report-detail.component';

describe('FraudInvestigationReport Management Detail Component', () => {
  let comp: FraudInvestigationReportDetailComponent;
  let fixture: ComponentFixture<FraudInvestigationReportDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FraudInvestigationReportDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ fraudInvestigationReport: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(FraudInvestigationReportDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(FraudInvestigationReportDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load fraudInvestigationReport on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.fraudInvestigationReport).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
