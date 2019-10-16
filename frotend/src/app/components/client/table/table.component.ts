import {Component, OnInit} from '@angular/core';
import {WorkoutTableService} from '../../../service/workout-table.service';
import {WorkoutTable} from '../../../model/workout-table';
import {Exercise} from '../../../model/exercise';
import {LoginService} from '../../../service/login.service';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {
  table: WorkoutTable;
  exerciseList: Exercise[];
  userOfTable: string;

  constructor(private _wtService: WorkoutTableService, private _loginService: LoginService) {
  }

  ngOnInit() {
    this._wtService.getWorkTable().subscribe((table) => {
      this.table = table;
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

  isAdminProperty() {
    return this.userOfTable === 'admin';
  }
}
