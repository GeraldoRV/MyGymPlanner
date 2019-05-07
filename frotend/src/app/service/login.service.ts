import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private _http: HttpClient) {
  }

  login(value: any, value2: any) {
    const user = new User();
    user.userName = value;
    user.password = value2;
    return this._http.post('http://localhost:8080/user', user);

  }
}
