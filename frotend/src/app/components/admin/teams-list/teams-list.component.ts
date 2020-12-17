import {Component, OnInit, ViewChild} from '@angular/core';
import {TeamService} from '../../../service/team.service';
import {Team} from '../../../model/team';
import {MatTableDataSource} from '@angular/material/table';
import {MatSort} from '@angular/material/sort';

@Component({
  selector: 'app-teams-list',
  templateUrl: './teams-list.component.html',
  styleUrls: ['./teams-list.component.css']
})
export class TeamsListComponent implements OnInit {
  teams: Team[];
  dataSource = null;
  @ViewChild(MatSort) sort: MatSort;
  displayedColumns: string[] = ['name', 'leader.name', 'members.length'];

  constructor(private _teamService: TeamService) {
  }

  ngOnInit() {
    sessionStorage.removeItem('rollback');
    this._teamService.getAllTeams().subscribe(result => {
      this.teams = result;
      this.dataSource = new MatTableDataSource(this.teams);
      setTimeout(() => this.dataSource.sort = this.sort);
    }, error => {
      console.log(error);
    });
  }

}
