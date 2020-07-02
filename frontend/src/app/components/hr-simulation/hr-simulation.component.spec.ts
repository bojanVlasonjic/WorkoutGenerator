import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HrSimulationComponent } from './hr-simulation.component';

describe('HrSimulationComponent', () => {
  let component: HrSimulationComponent;
  let fixture: ComponentFixture<HrSimulationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HrSimulationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HrSimulationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
