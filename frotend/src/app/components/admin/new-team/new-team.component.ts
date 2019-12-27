import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {TeamService} from '../../../service/team.service';
import {UserService} from '../../../service/user.service';
import {User} from '../../../model/user';
import {Router} from '@angular/router';

@Component({
  selector: 'app-new-team',
  templateUrl: './new-team.component.html',
  styleUrls: ['./new-team.component.css']
})
export class NewTeamComponent implements OnInit {
  teamForm: FormGroup;
  newMonitors: User[] = [];
  monitors: User[];

  constructor(private fb: FormBuilder, private _teamService: TeamService,
              private _userService: UserService, private _route: Router) {
  }

  ngOnInit() {
    this.teamForm = this.fb.group(
      {
        teamName: [''],
        leader: [''],
        monitors: [[]],
        members: [[]]
      }
    );
    this._userService.getAllMonitorsNotMembers().subscribe(result => {
      this.monitors = result;
    }, error => {
      console.log(error);
    });
  }

  moveRight() {
    const monitors = this.teamForm.controls.monitors.value;
    monitors.forEach(item => {
      this.newMonitors.push(item);
      const index = this.monitors.indexOf(item);
      if (index > -1) {
        this.monitors.splice(index, 1);
      }
    });

  }

  moveLeft() {
    const members = this.teamForm.controls.members.value;
    members.forEach(item => {
      this.monitors.push(item);
      const index = this.newMonitors.indexOf(item);
      if (index > -1) {
        this.newMonitors.splice(index, 1);
      }
    });


  }

  moveAllRight() {
    this.monitors.forEach(item => this.newMonitors.push(item));
    this.monitors = [];
  }

  moveAllLeft() {
    this.newMonitors.forEach(item => this.monitors.push(item));
    this.newMonitors = [];
  }

  onSubmit() {
    const name = this.teamForm.controls.teamName.value;
    const leader = this.teamForm.controls.leader.value;
    const members = this.newMonitors;
    this._teamService.createTeam(name, leader, members).subscribe(() => {
      this._route.navigate(['/teams']);
    }, error => {
      console.log(error);
    });
  }
}
