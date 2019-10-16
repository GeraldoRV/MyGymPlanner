import {Component, OnInit} from '@angular/core';
import {WorkoutTableService} from '../../../service/workout-table.service';
import {WorkoutTable} from '../../../model/workout-table';
import {Exercise} from '../../../model/exercise';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {
  table: WorkoutTable;
  exerciseList: Exercise[];

  constructor(private _wtService: WorkoutTableService) {
  }

  ngOnInit() {
    this._wtService.getWorkTable().subscribe((table) => {
      this.table = table;
      this.exerciseList = table.exerciseList;
      console.log(table);
    }, (err) => {
      console.log(err);
    });
  }

}
