import {Component, OnInit, ViewChild} from '@angular/core';
import {WorkoutTableService} from '../../../service/workout-table.service';
import {WorkoutTable} from '../../../model/workout-table';
import {Router} from '@angular/router';
import {LoginService} from '../../../service/login.service';
import {Gym} from '../../../model/gym';
import {NgbTabChangeEvent} from '@ng-bootstrap/ng-bootstrap';
import {faEye, faTrashAlt} from '@fortawesome/free-solid-svg-icons';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-tables',
  templateUrl: './tables.component.html',
  styleUrls: ['./tables.component.css']
})
export class TablesComponent implements OnInit {
  tables: WorkoutTable[];
  myTables: WorkoutTable[] = null;
  private gym: Gym;
  faEye = faEye;
  faTrashAlt = faTrashAlt;
  dataSourceRoutines = null;
  dataSourceMyRoutines = null;
  @ViewChild(MatSort) sort: MatSort;
  displayedColumns: string[] = ['name', 'level', 'options'];
  isColumnsMobile = false;

  constructor(private _route: Router, private _wtService: WorkoutTableService,
              private _loginService: LoginService) {
  }

  ngOnInit() {
    this.gym = this._loginService.getUser().gym;
    this._wtService.getAllWorkTableByGym(this.gym.id).subscribe((tables) => {
      this.tables = tables;
      this.dataSourceRoutines = new MatTableDataSource(this.tables);
      setTimeout(() => this.dataSourceRoutines.sort = this.sort);
    }, (err) => {
      console.log(err);
    });
  }

  seeRoutine(id: number) {
    this._wtService.setWorkTable(id);
    this._route.navigate(['/client/routine']).then();
  }

  beforeChange($event: NgbTabChangeEvent) {
    const id = $event.nextId;
    if (id === 'tab-myRoutines' && this.myTables === null) {
      this._wtService.getAllMyWorkTable(this._loginService.getUser().id).subscribe((tables) => {
        this.myTables = tables;
        this.dataSourceMyRoutines = new MatTableDataSource(this.myTables);
        setTimeout(() => this.dataSourceMyRoutines.sort = this.sort);
      }, error => {
        console.log(error);
      });
    }
  }

  deleteRoutine(id: number) {
    if (confirm('Are you sure to delete the routine?')) {
      this._wtService.deleteWorkoutTable(id).subscribe(() => {
        const indexToDelete = this.dataSourceMyRoutines.data.findIndex(routine => routine.id === id);
        this.dataSourceMyRoutines.data.splice(indexToDelete, 1);
        this.dataSourceMyRoutines._updateChangeSubscription();
      }, error => {
        console.log(error);
      });
    }
  }
}
