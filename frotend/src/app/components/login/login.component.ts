import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {LoginService} from '../../service/login.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private loginService: LoginService, private _route: Router) {
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
      this.loginService.setUser(user);
      console.log(user);
      this._route.navigate(['/home']);
    }, (error) => {
      console.log(error);
    });
  }
}
