import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../../service/login.service';
import {ClassDirectedService} from '../../../service/class-directed.service';
import {Gym} from '../../../model/gym';
import {ClassDirected} from '../../../model/class-directed';
import {NgbTabChangeEvent} from '@ng-bootstrap/ng-bootstrap';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-class-directed',
  templateUrl: './class-directed.component.html',
  styleUrls: ['./class-directed.component.css']
})
export class ClassDirectedComponent implements OnInit {
  private gym: Gym;
  classesOfMon: ClassDirected[] = null;
  classesOfTues: ClassDirected[] = null;
  classesOfWed: ClassDirected[] = null;
  classesOfThur: ClassDirected[] = null;
  classesOfFri: ClassDirected[] = null;
  classesOfSat: ClassDirected[] = null;
  classesOfSun: ClassDirected[] = null;
  activeId: string;


  constructor(private _loginService: LoginService, private _classDirectedService: ClassDirectedService,
              private _datePipe: DatePipe) {
  }

  ngOnInit() {
    this.gym = this._loginService.getUser().gym;
    const todayDate = new Date();
    const todayDayOfWeek = this._datePipe.transform(todayDate, 'EEEE');
    this.activeId = 'tab-' + todayDayOfWeek;

    this._classDirectedService.getAllClassesOfGymAndDay(this.gym.id, todayDayOfWeek).subscribe(res => {
      this.setClass(todayDayOfWeek, res);
    }, error => {
      console.log(error);
    });
  }

  public beforeChange($event: NgbTabChangeEvent) {
    const dayOfWeek = $event.nextId.substring(4);
    if (this.isNullList(dayOfWeek)) {
      this._classDirectedService.getAllClassesOfGymAndDay(this.gym.id, dayOfWeek).subscribe(res => {
        this.setClass(dayOfWeek, res);
      }, error => {
        console.log(error);
      });
    }
  }

  private isNullList(dayOfWeek: string) {
    switch (dayOfWeek) {
      case 'Monday':
        if (this.classesOfMon !== null) {
          return false;
        }
        break;

      case 'Tuesday':
        if (this.classesOfTues !== null) {
          return false;
        }
        break;

      case 'Wednesday':
        if (this.classesOfWed !== null) {
          return false;
        }
        break;

      case 'Thursday':
        if (this.classesOfThur !== null) {
          return false;
        }
        break;

      case 'Friday':
        if (this.classesOfFri !== null) {
          return false;
        }
        break;

      case 'Saturday':
        if (this.classesOfSat !== null) {
          return false;
        }
        break;

      case 'Sunday':
        if (this.classesOfSun !== null) {
          return false;
        }
    }
    return true;
  }

  private setClass(dayOfWeek: string, classes: ClassDirected[]) {
    switch (dayOfWeek) {
      case 'Monday':
        this.classesOfMon = classes;
        break;
      case 'Tuesday':
        this.classesOfTues = classes;
        break;
      case 'Wednesday':
        this.classesOfWed = classes;
        break;
      case 'Thursday':
        this.classesOfThur = classes;
        break;
      case 'Friday':
        this.classesOfFri = classes;
        break;
      case 'Saturday':
        this.classesOfSat = classes;
        break;
      case 'Sunday':
        this.classesOfSun = classes;
    }
  }
}
