import {WorkingHours} from '../model/working-hours';

export class UserTypeMonitorDto {
  id: number;
  name: string;
  userName: string;
  workingHours: WorkingHours;
  nClassAssigned: number;
  leader: boolean;
}
