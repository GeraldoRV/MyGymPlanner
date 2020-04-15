import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../../service/login.service';
import {TypeClassService} from '../../../service/type-class.service';
import {TypeClass} from '../../../model/type-class';

@Component({
  selector: 'app-class-list',
  templateUrl: './class-list.component.html',
  styleUrls: ['./class-list.component.css']
})
export class ClassListComponent implements OnInit {
  classes: TypeClass[];

  constructor(private logUser: LoginService, private typeClassService: TypeClassService) {
  }

  ngOnInit() {
    this.typeClassService.getAllTaughtClass().subscribe(res => {
      this.classes = res;
    }, error => console.log(error));
  }

}
