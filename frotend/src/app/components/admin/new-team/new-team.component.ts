import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {TeamService} from '../../../service/team.service';

@Component({
  selector: 'app-new-team',
  templateUrl: './new-team.component.html',
  styleUrls: ['./new-team.component.css']
})
export class NewTeamComponent implements OnInit {
  teamForm: FormGroup;
  newMonitors: string[] = [];
  monitors = ['monitor 1', 'monitor 2', 'monitor 4'];

  constructor(private fb: FormBuilder, private _teamService: TeamService) {
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
    const members = this.teamForm.controls.members.value;
    this._teamService.createTeam(name, leader, members);
  }
}
