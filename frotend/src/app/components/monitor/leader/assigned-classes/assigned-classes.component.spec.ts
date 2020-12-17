import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignedClassesComponent } from './assigned-classes.component';

describe('AssignedClassesComponent', () => {
  let component: AssignedClassesComponent;
  let fixture: ComponentFixture<AssignedClassesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssignedClassesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssignedClassesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
