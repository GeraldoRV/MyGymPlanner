import {Injectable} from '@angular/core';
import {Exercise} from '../model/exercise';

@Injectable()
export class ExerciseService {
  private exercise: Exercise;

  constructor() {
  }

  setExercise(exercise: Exercise) {
    this.exercise = exercise;
  }

  getExercise() {
    return this.exercise;
  }
}
