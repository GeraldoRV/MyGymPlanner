import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {LoginService} from '../../service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private loginService: LoginService) {
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
      console.log(user);
    }, (error) => {
      console.log(error);
    });
  }
}
