import {Gym} from './gym';
import {Exercise} from './exercise';

export class WorkoutTable {
  id: number;
  name: string;
  level: string;
  gym: Gym;
  exerciseList: Exercise[];
}
