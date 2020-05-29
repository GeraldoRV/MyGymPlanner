import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ExerciseType} from '../model/exercise-type';
import {Global} from '../utilities/global';

@Injectable()
export class ExerciseTypeService {
  private readonly baseUrl: string;

  constructor(private _http: HttpClient, private _global: Global) {
    this.baseUrl = _global.IpAddress + 'exerciseType';
  }

  getAllExerciseType() {
    return this._http.get<ExerciseType[]>(this.baseUrl);
  }
}
