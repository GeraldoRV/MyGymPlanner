import {UserTypeMonitorDto} from './user-type-monitor.dto';

export class TeamDto {
  id: number;
  name: string;
  leader: UserTypeMonitorDto;
  members: UserTypeMonitorDto[];
}
