import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnrollmentHeroComponent } from './enrollment-hero.component';

describe('EnrollmentHeroComponent', () => {
  let component: EnrollmentHeroComponent;
  let fixture: ComponentFixture<EnrollmentHeroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EnrollmentHeroComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EnrollmentHeroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
