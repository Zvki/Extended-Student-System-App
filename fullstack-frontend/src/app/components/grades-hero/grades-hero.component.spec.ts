import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GradesHeroComponent } from './grades-hero.component';

describe('GradesHeroComponent', () => {
  let component: GradesHeroComponent;
  let fixture: ComponentFixture<GradesHeroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GradesHeroComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GradesHeroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
