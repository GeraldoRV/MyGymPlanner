import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {LoginService} from '../../service/login.service';
import {Router} from '@angular/router';
import {User} from '../../model/user';

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
      this.navigate(user);
    }, (error) => {
      console.log(error);
    });
  }

  navigate(user: User) {
    if (user.rol === 'Cliente') {
      this._route.navigate(['/home']);
    } else {
      this._route.navigate(['/admin']);
    }
  }
}
