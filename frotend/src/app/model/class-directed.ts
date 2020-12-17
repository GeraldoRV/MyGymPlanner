import {TypeClass} from './type-class';
import {User} from './user';
import {ClassSchedule} from './class-schedule';

export class ClassDirected {
  id: number;
  typeClass: TypeClass;
  assignedMonitor: User;
  capacity: number;
  clientList: User[];
  classSchedule: ClassSchedule;
}
