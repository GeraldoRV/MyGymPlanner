import {Component, OnInit} from '@angular/core';
import {UserService} from '../../../service/user.service';
import {User} from '../../../model/user';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  users: User[];

  constructor(private _userService: UserService) {
  }

  ngOnInit() {
    this._userService.getAllUser().subscribe((users) => {
      this.users = users;
      console.log(users);
    }, (error) => {
      console.log(error);
    });
  }

}
