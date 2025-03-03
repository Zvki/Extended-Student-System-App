import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubjectPlaceholderComponent } from './subject-placeholder.component';

describe('SubjectPlaceholderComponent', () => {
  let component: SubjectPlaceholderComponent;
  let fixture: ComponentFixture<SubjectPlaceholderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SubjectPlaceholderComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SubjectPlaceholderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
