import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignedClassComponent } from './assigned-class.component';

describe('AssignedClassComponent', () => {
  let component: AssignedClassComponent;
  let fixture: ComponentFixture<AssignedClassComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssignedClassComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssignedClassComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
