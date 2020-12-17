import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {User} from '../../../model/user';
import {LoginService} from '../../../service/login.service';
import {UserService} from '../../../service/user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-new-user',
  templateUrl: './new-user.component.html',
  styleUrls: ['./new-user.component.css']
})
export class NewUserComponent implements OnInit {

  constructor(private fb: FormBuilder, private _loginService: LoginService,
              private _userService: UserService, private _route: Router) {
  }

  userAddForm: FormGroup;
  roles = [];
  submit = false;

  private static getRoles() {
    return ['socio', 'admin', 'monitor'];
  }

  ngOnInit() {
    sessionStorage.setItem('rollback', '/admin');
    this.userAddForm = this.fb.group(
      {
        name: ['', Validators.required],
        userName: ['', Validators.required],
        role: ['', Validators.required],
        password: [null]
      }
    );
    this.roles = NewUserComponent.getRoles();
  }

  onSubmit() {
    this.submit = true;
    if (this.userAddForm.valid) {
      const user = this.getUserDetails();

      this._userService.createUser(user).subscribe(() => {
        this._route.navigate(['/admin']).then();
      }, error => {
        console.log(error);
      });

    }
  }

  private getUserDetails(): User {
    let user;
    user = {...this.userAddForm.value} as User;
    user.gym = this._loginService.getUser().gym;
    return user;
  }

  get controls() {
    return this.userAddForm.controls;
  }

}
