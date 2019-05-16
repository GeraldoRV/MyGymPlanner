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
  userAddForm: FormGroup;
  roles = [];

  constructor(private fb: FormBuilder, private _loginService: LoginService,
              private _userService: UserService, private _route: Router) {
  }

  ngOnInit() {
    this.userAddForm = this.fb.group(
      {
        name: [''],
        username: [''],
        roles: ['']
      }
    );
    this.roles = this.getRoles();
  }

  private getRoles() {
    return ['cliente', 'admin'];
  }

  submit() {
    const user = new User();
    user.name = this.userAddForm.controls.name.value;
    user.gym = this._loginService.getUser().gym;
    user.rol = this.userAddForm.controls.roles.value;
    user.userName = this.userAddForm.controls.username.value;
    user.password = '1234';
    this._userService.createUser(user).subscribe(res => {
      console.log(res);
      this._route.navigate(['/admin']);
    }, error => {
      console.log(error);
    });
  }

}
