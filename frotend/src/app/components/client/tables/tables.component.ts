import {Component, OnInit} from '@angular/core';
import {WorkoutTableService} from '../../../service/workout-table.service';
import {WorkoutTable} from '../../../model/workout-table';
import {Router} from '@angular/router';
import {LoginService} from '../../../service/login.service';
import {Gym} from '../../../model/gym';

@Component({
  selector: 'app-tables',
  templateUrl: './tables.component.html',
  styleUrls: ['./tables.component.css']
})
export class TablesComponent implements OnInit {
  tables: WorkoutTable[];
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
}
