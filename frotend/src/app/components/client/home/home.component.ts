import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../../service/login.service';
import {Gym} from '../../../model/gym';
import {TypeClassService} from '../../../service/type-class.service';
import {TypeClass} from '../../../model/type-class';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  nameGym: string;
  directionGym: string;
  taughtClasses: TypeClass[];
  private gym: Gym;
  mondayToFriday: string;
  saturdays: string;
  sundaysAndHolidays: string;

  constructor(private _loginService: LoginService,
              private _typeClassService: TypeClassService) {
  }

  ngOnInit() {
    this.gym = this._loginService.getUser().gym;
    this.setGymDetails();
    this.getAllClasses();
  }

  private getAllClasses() {
    this._typeClassService.getAll().subscribe(res => {
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
