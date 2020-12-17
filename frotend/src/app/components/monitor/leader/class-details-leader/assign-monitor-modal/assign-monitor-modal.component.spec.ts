import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignMonitorModalComponent } from './assign-monitor-modal.component';

describe('AssignMonitorModalComponent', () => {
  let component: AssignMonitorModalComponent;
  let fixture: ComponentFixture<AssignMonitorModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssignMonitorModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssignMonitorModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
