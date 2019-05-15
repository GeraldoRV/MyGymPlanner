import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Gym} from '../model/gym';

@Injectable({
  providedIn: 'root'
})
export class GymService {

  constructor(private _http: HttpClient) {
  }

  getGym(id: number) {
    return this._http.get<Gym>('http://localhost:8080/gym/' + id);
  }
}
