import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ClassDirected} from '../model/class-directed';
import {Global} from '../utilities/global';

@Injectable({
  providedIn: 'root'
})
export class ClassDirectedService {
  private readonly baseUrl: string;

  constructor(private _http: HttpClient, private _global: Global) {
    this.baseUrl = _global.IpAddress + 'class';
  }

  getAllClassesOfGym(gym_id: number) {
    return this._http.get<ClassDirected[]>(this.baseUrl + '/gym/' + gym_id);
  }

  getAllClassesOfGymAndDay(gym_id: number, dayOfWeek: string) {
    return this._http.get<ClassDirected[]>(this.baseUrl + '/gym/' + gym_id + '/' + dayOfWeek);
  }

  getAllClassesOfMonitorAndDay(monitor_id: number, dayOfWeek: string) {
    return this._http.get<ClassDirected[]>(this.baseUrl + '/monitor/' + monitor_id + '/' + dayOfWeek);
  }

  addClientToClass(classDirected: ClassDirected, user_id: number) {
    return this._http.put(this.baseUrl + '/client/' + user_id, classDirected);
  }
}
