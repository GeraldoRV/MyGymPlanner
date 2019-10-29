import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {WorkoutTable} from '../model/workout-table';

@Injectable({
  providedIn: 'root'
})
export class WorkoutTableService {
  private id: number;
  private base_url = 'http://localhost:8080/table';

  constructor(private _http: HttpClient) {
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
}
