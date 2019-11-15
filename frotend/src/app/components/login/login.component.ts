import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {LoginService} from '../../service/login.service';
import {Router} from '@angular/router';
import {AlertService} from '../../service/alert.service';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  submit = false;

  constructor(private fb: FormBuilder, private loginService: LoginService,
              private _route: Router, private alertService: AlertService) {
  }

  ngOnInit() {
    this.loginForm = this.fb.group(
      {
        userName: ['', Validators.required],
        password: ['', Validators.required]
      });
  }

  onSubmit() {
    this.submit = true;
    if (this.loginForm.valid) {
      this.loginService.login(
        this.loginForm.controls.userName.value, this.loginForm.controls.password.value).subscribe((user) => {
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
    this.alertService.setMessage(error.error.message);
    this.alertService.setDismissible(false);
    this.alertService.show();
    this.alertService.setTimeout(5000);
  }

  get controls() {
    return this.loginForm.controls;
  }
}
