import {Component, OnInit} from '@angular/core';
import {WorkoutTableService} from '../../../service/workout-table.service';
import {WorkoutTable} from '../../../model/workout-table';
import {Router} from '@angular/router';
import {LoginService} from '../../../service/login.service';
import {Gym} from '../../../model/gym';
import {NgbTabChangeEvent} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-tables',
  templateUrl: './tables.component.html',
  styleUrls: ['./tables.component.css']
})
export class TablesComponent implements OnInit {
  tables: WorkoutTable[];
  myTables: WorkoutTable[] = null;
  private gym: Gym;

  constructor(private _route: Router, private _wtService: WorkoutTableService,
              private _loginService: LoginService) {
  }

  ngOnInit() {
    this.gym = this._loginService.getUser().gym;
    this._wtService.getAllWorkTableByGym(this.gym.id).subscribe((tables) => {
      this.tables = tables;
    }, (err) => {
      console.log(err);
    });
  }

  seeRoutine(id: number) {
    this._wtService.setWorkTable(id);
    this._route.navigate(['/routine']);
  }

  beforeChange($event: NgbTabChangeEvent) {
    const id = $event.nextId;
    if (id === 'tab-myRoutines' && this.myTables === null) {
      this._wtService.getAllMyWorkTable(this._loginService.getUser().id).subscribe((tables) => {
        this.myTables = tables;
      }, error => {
        console.log(error);
      });
    }
  }
}
