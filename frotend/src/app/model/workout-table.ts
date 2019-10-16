import {Exercise} from './exercise';
import {User} from './user';

export class WorkoutTable {
  id: number;
  name: string;
  level: string;
  user: User;
  exerciseList: Exercise[];
}
