import { TestBed } from '@angular/core/testing';

import { ClassDirectedService } from './class-directed.service';

describe('ClassDirectedService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ClassDirectedService = TestBed.get(ClassDirectedService);
    expect(service).toBeTruthy();
  });
});
