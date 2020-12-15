import {Component, OnInit, ViewChild} from '@angular/core';
import {TeamService} from '../../../../service/team.service';
import {LoginService} from '../../../../service/login.service';
import {TeamDto} from '../../../../dto/team.dto';
import {UserTypeMonitorDto} from '../../../../dto/user-type-monitor.dto';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-my-team',
  templateUrl: './my-team.component.html',
  styleUrls: ['./my-team.component.css']
})
export class MyTeamComponent implements OnInit {
  members: UserTypeMonitorDto[];
  myTeam: TeamDto;
  teamName: string;
  dataSource = null;
  @ViewChild(MatSort) sort: MatSort;
  displayedColumns: string[] = ['name', 'userName', 'nClassAssigned'];

  constructor(private _teamService: TeamService, private _loginUser: LoginService) {
  }

  ngOnInit() {
    const leader = this._loginUser.getUser();
    this._teamService.getTeamByLeader(leader.id).subscribe(team => {
      this.myTeam = team;
      this.members = team.members;
      this.teamName = team.name;
      this.dataSource = new MatTableDataSource(this.members);
      setTimeout(() => this.dataSource.sort = this.sort);
    }, error => {
      console.log(error);
    });
  }

}
