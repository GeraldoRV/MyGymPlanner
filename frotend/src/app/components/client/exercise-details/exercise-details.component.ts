import {Component, OnInit} from '@angular/core';
import {Exercise} from '../../../model/exercise';
import {ExerciseService} from '../../../service/exercise.service';
import {faPen, faTimes} from '@fortawesome/free-solid-svg-icons';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {WorkoutTableService} from '../../../service/workout-table.service';
import {WorkoutTable} from '../../../model/workout-table';
import {LoginService} from '../../../service/login.service';

@Component({
  selector: 'app-exercise-details',
  templateUrl: './exercise-details.component.html',
  styleUrls: ['./exercise-details.component.css']
})
export class ExerciseDetailsComponent implements OnInit {
  exercise: Exercise;
  faPen = faPen;
  modalForm: FormGroup;
  faTimes = faTimes;
  private table: WorkoutTable;
  role = false;

  constructor(private _exerciseService: ExerciseService, private fb: FormBuilder,
              private _modalService: NgbModal, private _wtService: WorkoutTableService, private _loginService: LoginService) {
  }

  ngOnInit() {
    this.exercise = this._exerciseService.getExercise();
    this._wtService.getWorkTable().subscribe(result => {
      this.table = result;
      this.exercise = this.table.exerciseList.find(value => value.id === this.exercise.id);
      if (this.table.user.role === 'client') {
        this.role = true;
      }
    }, (err) => {
      console.log(err);
    });
  }

  open(content) {
    this.builderForm();
    this._modalService.open(content);

  }

  private builderForm() {
    this.modalForm = this.fb.group(
      {
        sets: [this.exercise.sets,
          [Validators.required, Validators.min(1), Validators.max(30)]],
        repetitions: [this.exercise.repetitions,
          [Validators.required, Validators.min(1), Validators.max(200)]]

      }
    );
  }

  get controls() {
    return this.modalForm.controls;
  }

  onSubmit() {
    this.exercise.sets = this.modalForm.controls.sets.value;
    this.exercise.repetitions = this.modalForm.controls.repetitions.value;
    if (this.role) {

      this._wtService.updateWorkTable(this.table).subscribe(result => {
        this.table = result;
        this.exercise = this.table.exerciseList.find(value => value.id === this.exercise.id);
      }, (err) => {
        console.log(err);
      });
    } else {
      this.table.user = this._loginService.getUser();
      this._wtService.saveTable(this.table).subscribe(result => {
        this.table = result;
        this.exercise = this.table.exerciseList.find(value => value.id === this.exercise.id);
        this.role = true;
      }, (err) => {
        console.log(err);
      });
    }
    this._modalService.dismissAll();

  }
}
