import {Component, OnInit} from '@angular/core';
import {WorkoutTableService} from '../../../service/workout-table.service';
import {WorkoutTable} from '../../../model/workout-table';
import {Exercise} from '../../../model/exercise';
import {LoginService} from '../../../service/login.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {ExerciseType} from '../../../model/exercise-type';
import {ExerciseTypeService} from '../../../service/exercise-type.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AlertService} from '../../../service/alert.service';
import {faTrashAlt} from '@fortawesome/free-solid-svg-icons';

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
  faTrashAlt = faTrashAlt;

  constructor(private _wtService: WorkoutTableService, private _loginService: LoginService,
              private _modalService: NgbModal, private _exeTService: ExerciseTypeService,
              private fb: FormBuilder, private _alertService: AlertService) {
  }

  ngOnInit() {
    this._wtService.getWorkTable().subscribe((table) => {
      this.setRoutine(table);
    }, (err) => {
      console.log(err);
    });
  }

  private setRoutine(table: WorkoutTable) {
    this.table = table;
    this.nameTable = table.name;
    this.levelTable = table.level;
    this.exerciseList = table.exerciseList;
    this.userOfTable = table.user.role;
  }

  saveRoutine() {
    this.table.user = this._loginService.getUser();
    this._wtService.saveTable(this.table).subscribe(table => {
      this.setRoutine(table);
      this.routineUpdateMessageAlert();
    }, error => {
      console.log(error);
    });
  }

  remove(exercise: Exercise) {
    this.table.exerciseList.forEach((exerciseOfList, index) => {
      if (exerciseOfList.id === exercise.id) {
        this.table.exerciseList.splice(index, 1);
      }
    });
  }

  openAddExercisesModal(content) {
    this.addExerciseModal = true;
    this.builderAddExerciseForm();
    this.getAllExercisesType();
    this._modalService.open(content);

  }

  openUpdateExerciseModal(content, exercise: Exercise) {
    this.modifiedExercise = exercise;
    this.addExerciseModal = false;
    this.builderUpdateExerciseForm();
    this.getAllExercisesType();
    this._modalService.open(content).result.then(() => {
      this.modifiedExercise = null;
    }, () => {
      this.modifiedExercise = null;
    });
  }

  private builderAddExerciseForm() {
    this.modalTitle = 'Add Exercise';
    this.choose = 'Choose ';
    this.nameButtonAdd = 'Add Exercise';
    this.addForm = this.fb.group(
      {
        sets: ['',
          [Validators.required, Validators.min(1), Validators.max(30)]],
        repetitions: ['',
          [Validators.required, Validators.min(1), Validators.max(200)]],
        exerciseType: ['', Validators.required]
      }
    );
  }

  private builderUpdateExerciseForm() {
    this.modalTitle = 'Modify Exercise';
    this.nameButtonAdd = 'Change';
    this.choose = null;
    this.addForm = this.fb.group(
      {
        sets: [this.modifiedExercise.sets,
          [Validators.required, Validators.min(1), Validators.max(30)]],
        repetitions: [this.modifiedExercise.repetitions,
          [Validators.required, Validators.min(1), Validators.max(200)]],
        exerciseType: []
      }
    );
    this.addForm.controls.exerciseType.setValue(
      this.modifiedExercise.exerciseType, {onlySelf: true});
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

  onSubmit() {
    if (this.addForm.valid) {
      if (this.addExerciseModal) {
        this.submitNewExercises();
      } else {
        this.submitUpdateExercise();
      }
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

  private routineUpdateMessageAlert() {
    this._alertService.setType('success');
    this._alertService.setDismissible(false);
    this._alertService.setMessage('Updated done');
    this._alertService.show();
    this._alertService.setTimeout(2000);
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

  saveChanges() {
    this._wtService.updateWorkTable(this.table).subscribe((table) => {
      this.setRoutine(table);
      this.routineUpdateMessageAlert();
    }, error => {
      console.log(error);
    });
  }

  get controls() {
    return this.addForm.controls;
  }
}
