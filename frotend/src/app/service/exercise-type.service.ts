import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ExerciseType} from '../model/exercise-type';

@Injectable({
  providedIn: 'root'
})
export class ExerciseTypeService {
  private baseUrl = 'http://localhost:8080/exerciseType';

  constructor(private _http: HttpClient) {
  }

  getAllExerciseType() {
    return this._http.get<ExerciseType[]>(this.baseUrl);
  }
}
