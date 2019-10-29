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
  modalTitle: string;
  choose: string;
  private addExerciseModal = true;
  private modifiedExercise: Exercise = null;
  private newSets: any;
  private newRepetitions: any;
  nameButtonAdd: string;
  nameTable: string;
  levelTable: string;

  constructor(private _wtService: WorkoutTableService, private _loginService: LoginService,
              private _modalService: NgbModal, private _exeTService: ExerciseTypeService,
              private fb: FormBuilder) {
  }

  ngOnInit() {
    this._wtService.getWorkTable().subscribe((table) => {
      this.table = table;
      this.nameTable = table.name;
      this.levelTable = table.level;
      this.exerciseList = table.exerciseList;
      this.userOfTable = table.user.role;
    }, (err) => {
      console.log(err);
    });
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
    this.addExerciseModal = true;
    this.builderForm();
    this.getAllExercisesType();
    this._modalService.open(content);

  }

  private getAllExercisesType() {
    if (this.exerciseTypeList == null) {
      this._exeTService.getAllExerciseType().subscribe((res) => {
        this.exerciseTypeList = res;
        this.setDefault();
      }, error => {
        console.log(error);
      });
    } else {
      this.setDefault();
    }

  }

  private setDefault() {
    if (this.modifiedExercise != null) {
      const exerciseTypeDefault = this.exerciseTypeList.find(
        exerciseTypeInList => exerciseTypeInList.id === this.modifiedExercise.exerciseType.id);
      this.addForm.controls.exerciseType.setValue(exerciseTypeDefault, {onlySelf: true});
      this.addForm.controls.exerciseType.disable();
    }

  }

  private builderForm() {
    this.modalTitle = 'Add Exercise';
    this.choose = 'Choose ';
    this.nameButtonAdd = 'Add Exercise';
    this.addForm = this.fb.group(
      {
        sets: [''],
        repetitions: [''],
        exerciseType: ['']
      }
    );
  }

  onSubmit() {
    if (this.addExerciseModal) {
      this.submitNewExercises();
    } else {
      this.submitUpdateExercise();
    }
  }

  private submitUpdateExercise() {
    this.newSets = this.addForm.controls.sets.value;
    this.newRepetitions = this.addForm.controls.repetitions.value;
    this.addForm.reset();
    this.setDefault();
    this.nameButtonAdd = 'Click again to another change';
  }

  private submitNewExercises() {
    const newExercise = new Exercise;
    newExercise.exerciseType = this.addForm.controls.exerciseType.value;
    newExercise.sets = this.addForm.controls.sets.value;
    newExercise.repetitions = this.addForm.controls.repetitions.value;
    this.newExercises.push(newExercise);
    this.addForm.reset();
    this.nameButtonAdd = 'Add other exercise';

  }

  saveExercises() {
    if (this.addExerciseModal) {

      this.newExercises.forEach((exercise) => {
        this.table.exerciseList.push(exercise);
      });
    } else {
      this.modifiedExercise.sets = this.newSets;
      this.modifiedExercise.repetitions = this.newRepetitions;
      this.modifiedExercise = null;
    }
    this._modalService.dismissAll();
    this.other = '';

  }

  openModalChange(content, exercise: Exercise) {
    this.modifiedExercise = exercise;
    this.addExerciseModal = false;
    this.buildFormUpdate();
    this.getAllExercisesType();
    this._modalService.open(content);
  }

  private buildFormUpdate() {
    this.modalTitle = 'Modify Exercise';
    this.nameButtonAdd = 'Change';
    this.choose = null;
    this.addForm = this.fb.group(
      {
        sets: [this.modifiedExercise.sets],
        repetitions: [this.modifiedExercise.repetitions],
        exerciseType: []
      }
    );
    this.addForm.controls.exerciseType.setValue(
      this.modifiedExercise.exerciseType, {onlySelf: true});

  }

  saveChanges() {
    this._wtService.updateWorkTable(this.table).subscribe((table) => {
      this.table = table;
    }, error => {
      console.log(error);
    });
  }
}
