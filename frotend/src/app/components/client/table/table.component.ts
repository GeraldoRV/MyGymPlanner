import {Component, OnInit} from '@angular/core';
import {WorkoutTableService} from '../../../service/workout-table.service';
import {WorkoutTable} from '../../../model/workout-table';
import {Exercise} from '../../../model/exercise';
import {LoginService} from '../../../service/login.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {ExerciseType} from '../../../model/exercise-type';
import {ExerciseTypeService} from '../../../service/exercise-type.service';
import {FormBuilder, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {
  table: WorkoutTable;
  exerciseList: Exercise[];
  userOfTable: string;
  exerciseTypeList: ExerciseType[] = null;
  addForm: FormGroup;
  private newExercises: Exercise[] = [];
  other: string;

  constructor(private _wtService: WorkoutTableService, private _loginService: LoginService,
              private _modalService: NgbModal, private _exeTService: ExerciseTypeService,
              private fb: FormBuilder) {
  }

  ngOnInit() {
    this._wtService.getWorkTable().subscribe((table) => {
      this.table = table;
      this.exerciseList = table.exerciseList;
      this.userOfTable = table.user.role;
    }, (err) => {
      console.log(err);
    });
    // this.newExercises = [];
  }

  save() {
    this.table.user = this._loginService.getUser();
    this._wtService.saveTable(this.table).subscribe(table => {
      this.table = table;
      this.exerciseList = this.table.exerciseList;
      this.userOfTable = table.user.role;
    }, error => {
      console.log(error);
    });
  }

  remove(exercise: Exercise) {
    this.table.exerciseList.forEach((exer, index) => {
      if (exer.id === exercise.id) {
        this.table.exerciseList.splice(index, 1);
      }
    });
  }

  openModal(content) {
    this.builderForm();
    if (this.exerciseTypeList === null) {
      this._exeTService.getAllExerciseType().subscribe((res) => {
        this.exerciseTypeList = res;
      }, error => {
        console.log(error);
      });
    }
    this._modalService.open(content);

  }

  private builderForm() {
    this.addForm = this.fb.group(
      {
        sets: [''],
        repetitions: [''],
        exerciseType: ['']
      }
    );
  }

  onSubmit() {
    const newExercise = new Exercise;
    newExercise.exerciseType = this.addForm.controls.exerciseType.value;
    newExercise.sets = this.addForm.controls.sets.value;
    newExercise.repetitions = this.addForm.controls.repetitions.value;
    this.newExercises.push(newExercise);
    this.addForm.reset();
    this.other = 'other';
  }

  saveExercises() {
    this.newExercises.forEach((exercise) => {
      this.table.exerciseList.push(exercise);
    });
    this._modalService.dismissAll();
    this.other = '';

  }
}
