import {Gym} from './gym';
import {WorkingHours} from './working-hours';

export class User {
  id: number;
  name: string;
  userName: string;
  password: string;
  role: string;
  gym: Gym;
  workingHours: WorkingHours;
  leader: boolean;
}
