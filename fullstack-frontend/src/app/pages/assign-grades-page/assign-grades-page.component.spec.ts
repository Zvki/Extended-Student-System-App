import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignGradesPageComponent } from './assign-grades-page.component';

describe('AssignGradesPageComponent', () => {
  let component: AssignGradesPageComponent;
  let fixture: ComponentFixture<AssignGradesPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AssignGradesPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AssignGradesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
