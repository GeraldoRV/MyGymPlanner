import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ClassDirected} from '../model/class-directed';
import {Global} from '../utilities/global';
import {ClassDirectedToAssignDto} from '../dto/class-directed-to-assign.dto';

@Injectable({
  providedIn: 'root'
})
export class ClassDirectedService {
  private readonly baseUrl: string;
  private classDirectedId: number;

  constructor(private _http: HttpClient, private _global: Global) {
    this.baseUrl = _global.IpAddress + 'class';
  }

  getAllClassesOfTypeClass(typeClassId) {
    return this._http.get<ClassDirectedToAssignDto[]>(this.baseUrl + '/type-class/' + typeClassId);
  }

  getAllClassesOfGymAndDay(gym_id: number, dayOfWeek: string) {
    return this._http.get<ClassDirected[]>(this.baseUrl + '/gym/' + gym_id + '/' + dayOfWeek);
  }

  getAllClassesOfMonitorAndDay(monitor_id: number, dayOfWeek: string) {
    return this._http.get<ClassDirected[]>(this.baseUrl + '/monitor/' + monitor_id + '/' + dayOfWeek);
  }

  reserveClass(classDirected: ClassDirected, user_id: number) {
    return this._http.put(this.baseUrl + '/client/' + user_id, classDirected);
  }

  setClassDirected(id: number) {
    this.classDirectedId = id;
  }

  getClassDirected() {
    return this._http.get<ClassDirected>(this.baseUrl + '/' + this.classDirectedId);
  }

  addClientToClass(clientId: number, assignedClass: ClassDirected) {
    return this._http.put(this.baseUrl + '/add/client/' + clientId, assignedClass);
  }
}
