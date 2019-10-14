import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {LoginService} from '../../service/login.service';
import {Router} from '@angular/router';
import {User} from '../../model/user';
import {AlertService} from '../../service/alert.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private loginService: LoginService,
              private _route: Router, private alertService: AlertService) {
  }

  ngOnInit() {
    this.loginForm = this.fb.group(
      {
        userName: [''],
        password: ['']
      });
  }

  onSubmit() {
    this.loginService.login(
      this.loginForm.controls.userName.value, this.loginForm.controls.password.value).subscribe((user) => {
      if (user === null) {
        this.userNotFoundAlert();
      } else {
        this.loginService.setUser(user);
        this.navigate(user);
      }
    }, (error) => {
      console.log(error);
      this.errorMessageAlert(error);
    });
  }

  private errorMessageAlert(error: any) {
    this.alertService.setType('danger');
    this.alertService.setMessage(error.message);
    this.alertService.setDismissible(false);
    this.alertService.show();
  }

  private userNotFoundAlert() {
    this.alertService.setType('danger');
    this.alertService.setMessage('User not found');
    this.alertService.setDismissible(false);
    this.alertService.show();
  }

  navigate(user: User) {
    if (user.rol === 'Cliente' || user.rol === 'cliente') {
      this._route.navigate(['/home']);
    } else {
      this._route.navigate(['/admin']);
    }
  }
}
