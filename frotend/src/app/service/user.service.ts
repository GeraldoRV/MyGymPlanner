import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../model/user';
import {Global} from '../utilities/global';
import {UserTypeMonitorDto} from '../dto/user-type-monitor.dto';

@Injectable()
export class UserService {
  private readonly baseUrl: string;

  constructor(private _http: HttpClient, private _global: Global) {
    this.baseUrl = _global.IpAddress + 'user';
  }

  getAllUsers() {
    return this._http.get<User[]>(this.baseUrl);
  }

  createUser(user: User) {
    return this._http.post(this.baseUrl, user);
  }

  getAllClientsLike(name: string) {
    return this._http.get<User[]>(this.baseUrl + '/client/' + name);
  }

  getAllMonitorsNotMembers() {
    return this._http.get<User[]>(this.baseUrl + '/monitor/not-members');
  }

  getAllMonitorsOfTeamLeaderId(id) {
    return this._http.get<UserTypeMonitorDto[]>(this.baseUrl + '/monitor/team/leader/' + id);
  }
}
