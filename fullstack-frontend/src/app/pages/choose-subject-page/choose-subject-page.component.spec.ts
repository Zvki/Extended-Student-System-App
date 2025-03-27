import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChooseSubjectPageComponent } from './choose-subject-page.component';

describe('ChooseSubjectPageComponent', () => {
  let component: ChooseSubjectPageComponent;
  let fixture: ComponentFixture<ChooseSubjectPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChooseSubjectPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ChooseSubjectPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
