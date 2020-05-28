import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClassDetailsLeaderComponent } from './class-details-leader.component';

describe('ClassDetailsLeaderComponent', () => {
  let component: ClassDetailsLeaderComponent;
  let fixture: ComponentFixture<ClassDetailsLeaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClassDetailsLeaderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClassDetailsLeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
