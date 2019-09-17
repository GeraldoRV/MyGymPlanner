import { TestBed } from '@angular/core/testing';

import { WorkoutTableService } from './workout-table.service';

describe('WorkoutTableService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: WorkoutTableService = TestBed.get(WorkoutTableService);
    expect(service).toBeTruthy();
  });
});
