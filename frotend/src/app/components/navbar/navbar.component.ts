import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {LoginService} from '../../service/login.service';
import {User} from '../../model/user';
import {faArrowLeft} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']

})
export class NavbarComponent implements OnInit {
  userLogin: User;
  navbarOpen = false;
  faArrowLeft = faArrowLeft;

  constructor(private _route: Router, public loginService: LoginService) {
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
    return this.loginService.getUserRole();
  }

  toggleNavBar() {
    this.navbarOpen = !this.navbarOpen;

  }

  isLeader() {
    return this.userLogin.leader;
  }

  rollBack() {
    this._route.navigate([sessionStorage.getItem('rollback')]);
  }

  thereAreRollBack() {
    return sessionStorage.getItem('rollback') !== null;
  }
}
