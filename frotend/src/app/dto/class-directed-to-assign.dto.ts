import {User} from '../model/user';
import {ClassSchedule} from '../model/class-schedule';

export class ClassDirectedToAssignDto {
  id: number;
  assignedMonitor: User;
  classSchedule: ClassSchedule;
}
