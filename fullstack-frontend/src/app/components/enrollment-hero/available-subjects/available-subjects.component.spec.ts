import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvailableSubjectsComponent } from './available-subjects.component';

describe('AvailableSubjectsComponent', () => {
  let component: AvailableSubjectsComponent;
  let fixture: ComponentFixture<AvailableSubjectsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AvailableSubjectsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AvailableSubjectsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
