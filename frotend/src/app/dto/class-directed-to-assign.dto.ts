import {ClassSchedule} from '../model/class-schedule';
import {UserTypeMonitorDto} from './user-type-monitor.dto';

export class ClassDirectedToAssignDto {
  id: number;
  assignedMonitor: UserTypeMonitorDto;
  classSchedule: ClassSchedule;
}
