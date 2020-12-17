import { TestBed } from '@angular/core/testing';

import { TypeClassService } from './type-class.service';

describe('TypeClassService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TypeClassService = TestBed.get(TypeClassService);
    expect(service).toBeTruthy();
  });
});
