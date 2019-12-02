import {Component, OnInit} from '@angular/core';
import {TeamService} from '../../../service/team.service';
import {Team} from '../../../model/team';

@Component({
  selector: 'app-teams-list',
  templateUrl: './teams-list.component.html',
  styleUrls: ['./teams-list.component.css']
})
export class TeamsListComponent implements OnInit {
  teams: Team[];

  constructor(private _teamService: TeamService) {
  }

  ngOnInit() {
    this._teamService.getAllTeams().subscribe(result => {
      this.teams = result;
      console.log(result);
    }, error => {
      console.log(error);
    });
  }

}
