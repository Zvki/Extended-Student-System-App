import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnrolledSubjectsComponent } from './enrolled-subjects.component';

describe('EnrolledSubjectsComponent', () => {
  let component: EnrolledSubjectsComponent;
  let fixture: ComponentFixture<EnrolledSubjectsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EnrolledSubjectsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EnrolledSubjectsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
