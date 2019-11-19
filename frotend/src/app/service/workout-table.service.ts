import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {WorkoutTable} from '../model/workout-table';
import {Global} from '../utilities/global';

@Injectable({
  providedIn: 'root'
})
export class WorkoutTableService {
  private id: number;
  private readonly base_url: string;

  constructor(private _http: HttpClient, private _global: Global) {
    this.base_url = _global.IpAddress + 'table';
  }

  getAllWorkTableByGym(gymId: number) {
    return this._http.get<WorkoutTable[]>(this.base_url + '/gym/' + gymId);
  }

  setWorkTable(id: number) {
    this.id = id;
  }

  getWorkTable() {
    return this._http.get<WorkoutTable>(this.base_url + '/' + this.id);
  }

  saveTable(workoutTable: WorkoutTable) {
    return this._http.put<WorkoutTable>(this.base_url + '/client', workoutTable);
  }

  getAllMyWorkTable(userId: number) {
    return this._http.get<WorkoutTable[]>(this.base_url + '/user/' + userId);
  }

  updateWorkTable(workoutTable: WorkoutTable) {
    return this._http.put<WorkoutTable>(this.base_url, workoutTable);
  }

  deleteWorkoutTable(workoutTableId: number) {
    return this._http.delete(this.base_url + '/' + workoutTableId);
  }
}
