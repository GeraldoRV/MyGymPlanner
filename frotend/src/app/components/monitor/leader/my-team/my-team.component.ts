import {Component, OnInit} from '@angular/core';
import {User} from '../../../../model/user';
import {TeamService} from '../../../../service/team.service';
import {LoginService} from '../../../../service/login.service';
import {Team} from '../../../../model/team';

@Component({
  selector: 'app-my-team',
  templateUrl: './my-team.component.html',
  styleUrls: ['./my-team.component.css']
})
export class MyTeamComponent implements OnInit {
  members: User[];
  myTeam: Team;
  teamName: string;

  constructor(private _teamService: TeamService, private _loginUser: LoginService) {
  }

  ngOnInit() {
    const leader = this._loginUser.getUser();
    this._teamService.getTeamByLeader(leader.id).subscribe(team => {
      this.myTeam = team;
      this.members = team.members;
      this.teamName = team.name;
    }, error => {
      console.log(error);
    });
  }

}
