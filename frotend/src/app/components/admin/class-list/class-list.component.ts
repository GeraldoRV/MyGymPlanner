import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../../service/login.service';
import {TypeClassService} from '../../../service/type-class.service';
import {TypeClassAdminDto} from '../../../dto/type-class-admin.dto';

@Component({
  selector: 'app-class-list',
  templateUrl: './class-list.component.html',
  styleUrls: ['./class-list.component.css']
})
export class ClassListComponent implements OnInit {
  classes: TypeClassAdminDto[];

  constructor(private logUser: LoginService, private typeClassService: TypeClassService) {
  }

  ngOnInit() {
    this.typeClassService.getAllTaughtClass().subscribe(res => {
      this.classes = res;
      console.log(res);
    }, error => console.log(error));
  }

}
