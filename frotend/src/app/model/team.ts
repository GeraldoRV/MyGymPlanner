import {User} from './user';

export class Team {
  id: number;
  name: string;
  leader: User;
  members: User[];
}
