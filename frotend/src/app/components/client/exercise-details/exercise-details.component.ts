import {Component, OnInit} from '@angular/core';
import {Exercise} from '../../../model/exercise';
import {ExerciseService} from '../../../service/exercise.service';

@Component({
  selector: 'app-exercise-details',
  templateUrl: './exercise-details.component.html',
  styleUrls: ['./exercise-details.component.css']
})
export class ExerciseDetailsComponent implements OnInit {
  exercise: Exercise;

  constructor(private _exerciseService: ExerciseService) {
  }

  ngOnInit() {
    this.exercise = this._exerciseService.getExercise();
  }

}
