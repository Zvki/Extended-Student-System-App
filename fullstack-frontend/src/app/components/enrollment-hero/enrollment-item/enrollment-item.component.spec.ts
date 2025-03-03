import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnrollmentItemComponent } from './enrollment-item.component';

describe('EnrollmentItemComponent', () => {
  let component: EnrollmentItemComponent;
  let fixture: ComponentFixture<EnrollmentItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EnrollmentItemComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EnrollmentItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
