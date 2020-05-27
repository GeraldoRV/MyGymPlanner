import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {LoginService} from '../../service/login.service';
import {User} from '../../model/user';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  userLogin: User;
  navbarOpen = false;

  constructor(private _route: Router, private loginService: LoginService) {
  }

  ngOnInit() {
    this.userLogin = this.loginService.getUser();
  }

  logout() {
    this.loginService.logout();
    this._route.navigate(['']).then();
    this.toggleNavBar();
  }

  getRole() {
    this.userLogin = this.loginService.getUser();
    return this.userLogin.role;
  }

  toggleNavBar() {
    this.navbarOpen = !this.navbarOpen;

  }

  isLeader() {
    return this.userLogin.leader;
  }
}
