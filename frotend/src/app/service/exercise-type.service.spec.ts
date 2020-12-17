import { TestBed } from '@angular/core/testing';

import { ExerciseTypeService } from './exercise-type.service';

describe('ExerciseTypeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ExerciseTypeService = TestBed.get(ExerciseTypeService);
    expect(service).toBeTruthy();
  });
});
