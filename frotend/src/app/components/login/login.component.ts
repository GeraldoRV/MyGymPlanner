import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {LoginService} from '../../service/login.service';
import {Router} from '@angular/router';
import {AlertService} from '../../service/alert.service';
import {HttpErrorResponse} from '@angular/common/http';
import {faKey, faUser} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  submit = false;
  faKey = faKey;
  faUser = faUser;

  constructor(private fb: FormBuilder, private loginService: LoginService,
              private _route: Router, private alertService: AlertService) {
  }

  ngOnInit() {
    sessionStorage.removeItem('rollback');
    if (this.loginService.isLoginIn()) {
      this._route.navigate([this.loginService.getUserRole()]);
      return;
    }
    this.loginForm = this.fb.group(
      {
        userName: ['', Validators.required],
        password: ['', Validators.required]
      });
  }

  onSubmit() {
    this.submit = true;
    if (this.loginForm.valid) {
      const username = this.loginForm.controls.userName.value.toLowerCase();
      this.loginService.login(
        username, this.loginForm.controls.password.value).subscribe((user) => {
        this.loginService.setUser(user);
        this._route.navigate([user.role]).then();
      }, (error) => {
        console.log(error);
        this.errorMessageAlert(error);
      });
    }
  }

  private errorMessageAlert(error: HttpErrorResponse) {
    this.alertService.setType('danger');
    let message = error.error.message;
    if (error.error.status === 404) {
      message = 'Usuario o contraseña incorrectos.';
    }
    this.alertService.setMessage(message);
    this.alertService.setDismissible(false);
    this.alertService.show();
    this.alertService.setTimeout(5000);
  }

  get controls() {
    return this.loginForm.controls;
  }
}
