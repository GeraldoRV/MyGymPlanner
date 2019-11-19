import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../model/user';
import {Global} from '../utilities/global';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private user: User = null;

  constructor(private _http: HttpClient, private _global: Global) {
  }

  login(value: any, value2: any) {
    const user = new User();
    user.userName = value;
    user.password = value2;
    return this._http.post<User>(this._global.IpAddress + 'login', user);

  }

  setUser(user: User) {
    this.user = user;
  }

  getUser(): User {
    return this.user;
  }

  logout() {
    this.user = null;
  }

  isLoginIn() {
    return this.user !== null;
  }
}
