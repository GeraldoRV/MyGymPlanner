import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Global} from '../utilities/global';
import {Team} from '../model/team';

@Injectable({
  providedIn: 'root'
})
export class TeamService {
  private readonly baseUrl: string;

  constructor(private _http: HttpClient, private _global: Global) {
    this.baseUrl = _global.IpAddress + 'team';
  }

  getAllTeams() {
    return this._http.get<Team[]>(this.baseUrl);
  }

  createTeam(name: any, leader, members) {
    const team = new Team();
    team.name = name;
    team.leader = leader;
    team.members = members;
    return this._http.post(this.baseUrl, team);
  }

  getTeamByLeader(id: number) {
    return this._http.get<Team>(this.baseUrl + '/leader/' + id);
  }
}
