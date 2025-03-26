import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChooseSubjectComponent } from './choose-subject.component';

describe('ChooseSubjectComponent', () => {
  let component: ChooseSubjectComponent;
  let fixture: ComponentFixture<ChooseSubjectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChooseSubjectComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ChooseSubjectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
