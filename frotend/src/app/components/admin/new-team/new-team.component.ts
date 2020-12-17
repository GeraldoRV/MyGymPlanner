import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {TeamService} from '../../../service/team.service';
import {UserService} from '../../../service/user.service';
import {User} from '../../../model/user';
import {Router} from '@angular/router';
import * as _ from 'lodash';
import {
  faChevronCircleDown,
  faChevronCircleLeft,
  faChevronCircleRight,
  faChevronCircleUp
} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-new-team',
  templateUrl: './new-team.component.html',
  styleUrls: ['./new-team.component.css']
})
export class NewTeamComponent implements OnInit {
  teamForm: FormGroup;
  newMonitors: User[] = [];
  monitors: User[];
  submit = false;
  leaders: User[];
  faChevronRight = faChevronCircleRight;
  faChevronLeft = faChevronCircleLeft;
  faChevronUp = faChevronCircleUp;
  faChevronDown = faChevronCircleDown;

  constructor(private fb: FormBuilder, private _teamService: TeamService,
              private _userService: UserService, private _route: Router) {
  }

  ngOnInit() {
    sessionStorage.setItem('rollback', '/admin/teams');
    this.teamForm = this.fb.group(
      {
        teamName: [null, Validators.required],
        leader: [null, Validators.required],
        monitors: [[]],
        members: [[]]
      }
    );
    this._userService.getAllMonitorsNotMembers().subscribe(result => {
      this.monitors = result;
      this.leaders = _.clone(this.monitors);
    }, error => {
      console.log(error);
    });
  }

  moveRight() {
    this.added('monitors', this.newMonitors, this.monitors);
  }

  moveLeft() {
    this.added('members', this.monitors, this.newMonitors);
  }

  added(monitorControl: string, monitorsToAdd: User [], monitorsToQuit: User[]) {
    const monitorsChoose = this.teamForm.controls[monitorControl].value;
    if (monitorsChoose !== null) {
      monitorsChoose.forEach(monitor => {
        monitorsToAdd.push(monitor);
        const index = monitorsToQuit.indexOf(monitor);
        if (index > -1) {
          monitorsToQuit.splice(index, 1);
        }
      });
      this.teamForm.controls[monitorControl].reset();
    }
  }

  onSubmit() {
    if (!this.teamForm.valid) {
      this.submit = true;
      return;
    }
    const name = this.teamForm.controls.teamName.value;
    const leader = this.teamForm.controls.leader.value;
    const members = this.newMonitors;
    this._teamService.createTeam(name, leader, members).subscribe(() => {
      this._route.navigate(['/admin/teams']);
    }, error => {
      console.log(error);
    });
  }

  get controls() {
    return this.teamForm.controls;
  }
}
