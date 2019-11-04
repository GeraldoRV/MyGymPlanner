import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
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

  private static getRoles() {
    return ['client', 'admin', 'monitor'];
  }

  ngOnInit() {
    this.userAddForm = this.fb.group(
      {
        name: [''],
        username: [''],
        roles: ['']
      }
    );
    this.roles = NewUserComponent.getRoles();
  }

  submit() {
    const user = this.getUserDetails();

    this._userService.createUser(user).subscribe(() => {
      this._route.navigate(['/admin']).then();
    }, error => {
      console.log(error);
    });
  }

  private getUserDetails(): User {
    const user = new User();
    user.name = this.userAddForm.controls.name.value;
    user.gym = this._loginService.getUser().gym;
    user.role = this.userAddForm.controls.roles.value;
    user.userName = this.userAddForm.controls.username.value;
    user.password = '1234';
    return user;
  }

}
