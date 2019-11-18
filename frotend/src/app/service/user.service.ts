import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = 'http://localhost:8080/user';

  constructor(private _http: HttpClient) {
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
}
