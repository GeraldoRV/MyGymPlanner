import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../service/login.service';
import {User} from '../../model/user';

@Component({
  selector: 'app-main-monitor',
  templateUrl: './main-monitor.component.html',
  styleUrls: ['./main-monitor.component.css']
})
export class MainMonitorComponent implements OnInit {
  private user: User;
  mondayToFriday;
  saturday;
  sunday;
  constructor(private _loginService: LoginService) {
  }

  ngOnInit() {
    this.user = this._loginService.getUser();
    this.setWorkingHours();
  }

  private setWorkingHours() {
    this.mondayToFriday = this.user.workingHours.mondayToFriday;
    this.saturday = this.user.workingHours.saturday;
    this.sunday = this.user.workingHours.sunday;
  }

}
