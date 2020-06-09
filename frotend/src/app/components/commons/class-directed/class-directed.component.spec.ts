import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClassDirectedComponent } from './class-directed.component';

describe('ClassDirectedComponent', () => {
  let component: ClassDirectedComponent;
  let fixture: ComponentFixture<ClassDirectedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClassDirectedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClassDirectedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
