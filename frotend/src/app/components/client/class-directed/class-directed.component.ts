import { Component, OnInit } from '@angular/core';
import {LoginService} from '../../../service/login.service';
import {ClassDirectedService} from '../../../service/class-directed.service';
import {Gym} from '../../../model/gym';
import {ClassDirected} from '../../../model/class-directed';

@Component({
  selector: 'app-class-directed',
  templateUrl: './class-directed.component.html',
  styleUrls: ['./class-directed.component.css']
})
export class ClassDirectedComponent implements OnInit {
  private gym: Gym;
  private classes: ClassDirected[];

  constructor(private _loginService: LoginService, private _classDirectedService: ClassDirectedService) { }

  ngOnInit() {
    this.gym = this._loginService.getUser().gym;
    this._classDirectedService.getAllClassesOfGym(this.gym.id).subscribe(res=> {
      this.classes = res;
    }, error => {
      console.log(error);
    });
  }

}
