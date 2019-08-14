import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {LoginService} from '../../service/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private _route: Router, private loginService: LoginService) {
  }

  ngOnInit() {
  }

  logout() {
    this.loginService.logout();
    this._route.navigate(['']);

  }
}
