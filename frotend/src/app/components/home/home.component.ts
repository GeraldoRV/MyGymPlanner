import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../service/login.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  nameGym: string;

  constructor(private _loginService: LoginService) {
  }

  ngOnInit() {
    const user = this._loginService.getUser();
    this.nameGym = user.gym.name;
  }

}
