import {Team} from '../model/team';

export class TypeClassAdminDto {
  id: number;
  name: string;
  nClassesDirected: number;
  team: Team;
}
