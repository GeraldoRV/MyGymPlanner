import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ClassDirected} from '../model/class-directed';

@Injectable({
  providedIn: 'root'
})
export class ClassDirectedService {
  private baseUrl = 'http://localhost:8080/class';

  constructor(private _http: HttpClient) {
  }

  getAllClassesOfGym(gym_id: number) {
    return this._http.get<ClassDirected[]>(this.baseUrl + '/gym/' + gym_id);
  }

  getAllClassesOfGymAndDay(gym_id: number, dayOfWeek: string) {
    return this._http.get<ClassDirected[]>(this.baseUrl + '/gym/' + gym_id + '/' + dayOfWeek);
  }
}
