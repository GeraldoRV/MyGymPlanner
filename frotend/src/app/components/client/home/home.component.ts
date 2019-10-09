import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../../service/login.service';
import {ClassDirected} from '../../../model/class-directed';
import {ClassDirectedService} from '../../../service/class-directed.service';
import {User} from '../../../model/user';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  nameGym: string;
  directionGym: string;
  taughtClasses: ClassDirected[];
  private user: User;

  constructor(private _loginService: LoginService,
              private _classDirectedService: ClassDirectedService) {
  }

  ngOnInit() {
    this.user = this._loginService.getUser();
    this.nameGym = this.user.gym.name;
    this.directionGym = this.user.gym.direction;
    this.getAllClasses();
  }

  private getAllClasses() {
    this._classDirectedService.getAllClassesOfGym(this.user.gym.id).subscribe(res => {
      this.taughtClasses = res;
    }, error => {
      console.log(error);
    });

  }

}
