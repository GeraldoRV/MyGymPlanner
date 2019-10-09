import {TypeClass} from './type-class';
import {User} from './user';

export class ClassDirected {
  id: number;
  typeClass: TypeClass;
  assignedMonitor: User;
  capacity: number;
  clientList: User[];
}
