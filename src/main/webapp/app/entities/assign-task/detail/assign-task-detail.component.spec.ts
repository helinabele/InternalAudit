import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AssignTaskDetailComponent } from './assign-task-detail.component';

describe('AssignTask Management Detail Component', () => {
  let comp: AssignTaskDetailComponent;
  let fixture: ComponentFixture<AssignTaskDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AssignTaskDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ assignTask: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(AssignTaskDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(AssignTaskDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load assignTask on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.assignTask).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
