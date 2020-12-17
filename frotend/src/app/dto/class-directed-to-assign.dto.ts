import {ClassSchedule} from '../model/class-schedule';
import {UserTypeMonitorDto} from './user-type-monitor.dto';
import {TypeClassLeaderDto} from './type-class-leader.dto';

export class ClassDirectedToAssignDto {
  id: number;
  assignedMonitor: UserTypeMonitorDto;
  classSchedule: ClassSchedule;
  typeClass: TypeClassLeaderDto;
}
