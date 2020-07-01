import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GenerateWorkoutComponent } from './generate-workout.component';

describe('GenerateWorkoutComponent', () => {
  let component: GenerateWorkoutComponent;
  let fixture: ComponentFixture<GenerateWorkoutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GenerateWorkoutComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GenerateWorkoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
