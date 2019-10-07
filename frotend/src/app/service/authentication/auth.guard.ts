import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {LoginService} from '../login.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private _loginService: LoginService, private router: Router) {
  }

  canActivate() {
    if (!this._loginService.isLoginIn()) {
      this.router.navigate(['']);
      return false;
    } else {
      return true;
    }
  }
}
