import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../../service/login.service';
import {ClassDirected} from '../../../model/class-directed';
import {ClassDirectedService} from '../../../service/class-directed.service';
import {Gym} from '../../../model/gym';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  nameGym: string;
  directionGym: string;
  taughtClasses: ClassDirected[];
  private gym: Gym;
  mondayToFriday: string;
  saturdays: string;
  sundaysAndHolidays: string;

  constructor(private _loginService: LoginService,
              private _classDirectedService: ClassDirectedService) {
  }

  ngOnInit() {
    this.gym = this._loginService.getUser().gym;
    this.setGymDetails();
    this.getAllClasses();
  }

  private getAllClasses() {
    this._classDirectedService.getAllClassesOfGym(this.gym.id).subscribe(res => {
      this.taughtClasses = res;
    }, error => {
      console.log(error);
    });

  }

  private setGymDetails() {
    this.nameGym = this.gym.name;
    this.directionGym = this.gym.direction;
    this.mondayToFriday = this.gym.openingHours.mondaysToFridays;
    this.saturdays = this.gym.openingHours.saturdays;
    this.sundaysAndHolidays = this.gym.openingHours.sundaysAndHolidays;
  }
}
