import {Component, OnInit, ViewChild} from '@angular/core';
import {UserService} from '../../../service/user.service';
import {User} from '../../../model/user';
import {MatTableDataSource} from '@angular/material/table';
import {MatSort} from '@angular/material/sort';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  users: User[];
  dataSource = null;
  @ViewChild(MatSort) sort: MatSort;
  displayedColumns: string[] = ['name', 'userName', 'role'];

  constructor(private _userService: UserService) {
  }

  ngOnInit() {
    this._userService.getAllUsers().subscribe((users) => {
      this.users = users;
      this.dataSource = new MatTableDataSource(this.users);
      setTimeout(() => this.dataSource.sort = this.sort);
    }, (error) => {
      console.log(error);
    });
  }
  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
