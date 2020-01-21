import {User} from './user';
import {TypeClass} from './type-class';

export class Team {
  id: number;
  name: string;
  leader: User;
  members: User[];
  classes: TypeClass[];
}
