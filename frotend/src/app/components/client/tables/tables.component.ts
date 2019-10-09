import {Component, OnInit} from '@angular/core';
import {WorkoutTableService} from '../../../service/workout-table.service';
import {WorkoutTable} from '../../../model/workout-table';
import {Router} from '@angular/router';

@Component({
  selector: 'app-tables',
  templateUrl: './tables.component.html',
  styleUrls: ['./tables.component.css']
})
export class TablesComponent implements OnInit {
  tables: WorkoutTable[];

  constructor(private _route: Router, private _wtService: WorkoutTableService) {
  }

  ngOnInit() {
    this._wtService.getAllWorkTable().subscribe((tables) => {
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
