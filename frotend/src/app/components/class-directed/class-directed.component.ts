import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../service/login.service';
import {ClassDirectedService} from '../../service/class-directed.service';
import {ClassDirected} from '../../model/class-directed';
import {NgbTabChangeEvent} from '@ng-bootstrap/ng-bootstrap';
import {DatePipe} from '@angular/common';
import {User} from '../../model/user';
import {Router} from '@angular/router';
import {ClassSchedule} from '../../model/class-schedule';

@Component({
  selector: 'app-class-directed',
  templateUrl: './class-directed.component.html',
  styleUrls: ['./class-directed.component.css']
})
export class ClassDirectedComponent implements OnInit {
  private user: User;
  classesOfMon: ClassDirected[] = null;
  classesOfTues: ClassDirected[] = null;
  classesOfWed: ClassDirected[] = null;
  classesOfThur: ClassDirected[] = null;
  classesOfFri: ClassDirected[] = null;
  classesOfSat: ClassDirected[] = null;
  classesOfSun: ClassDirected[] = null;
  activeId: string;


  constructor(private _loginService: LoginService, private _classDirectedService: ClassDirectedService,
              private _datePipe: DatePipe, private _route: Router) {
  }

  ngOnInit() {
    this.user = this._loginService.getUser();
    const todayDate = new Date();
    const todayDayOfWeek = this._datePipe.transform(todayDate, 'EEEE');
    this.activeId = 'tab-' + todayDayOfWeek;
    if (this.isClient()) {
      this.setClassesForClient(this.user.gym.id, todayDayOfWeek);
    } else {
      this.setClassesForMonitor(this.user.id, todayDayOfWeek);
    }
  }

  public beforeChange($event: NgbTabChangeEvent) {
    const dayOfWeek = $event.nextId.substring(4);
    if (this.isNullList(dayOfWeek)) {
      if (this.isClient()) {
        this.setClassesForClient(this.user.gym.id, dayOfWeek);
      } else {
        this.setClassesForMonitor(this.user.id, dayOfWeek);
      }
    }
  }

  public addClientToClass(classDirected: ClassDirected) {
    this._classDirectedService.reserveClass(classDirected, this.user.id).subscribe((res) => {
      if (res === true) {
        classDirected.clientList.push(this.user);
        alert('Added');
      } else {
        alert('Something wrong');
      }
    }, error => {
      console.log(error);
      alert(error.error.message);
    });
  }

  public seeClass(id: number) {
    this._classDirectedService.setClassDirected(id);
    this._route.navigate(['/monitor/assigned-class']);
  }

  public isTheCorrectTime(classSchedule: ClassSchedule) {
    const today = new Date();
    const todayDayOfWeek = this._datePipe.transform(today, 'EEEE');
    return classSchedule.dayOfWeek === todayDayOfWeek;
  }

  private setClassesForMonitor(userId: number, dayOfWeek: string) {
    this._classDirectedService.getAllClassesOfMonitorAndDay(userId, dayOfWeek).subscribe(res => {
      this.setClass(dayOfWeek, res);
    }, error => {
      console.log(error);
    });
  }

  private setClassesForClient(gymId: number, dayOfWeek: string) {
    this._classDirectedService.getAllClassesOfGymAndDay(gymId, dayOfWeek).subscribe(res => {
      this.setClass(dayOfWeek, res);
    }, error => {
      console.log(error);
    });
  }

  isClient() {
    return this.user.role === 'client';
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
}
