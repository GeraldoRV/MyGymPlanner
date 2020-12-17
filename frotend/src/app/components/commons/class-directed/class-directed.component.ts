import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../../service/login.service';
import {ClassDirectedService} from '../../../service/class-directed.service';
import {ClassDirected} from '../../../model/class-directed';
import {NgbModal, NgbTabChangeEvent} from '@ng-bootstrap/ng-bootstrap';
import {DatePipe} from '@angular/common';
import {User} from '../../../model/user';
import {Router} from '@angular/router';
import {ClassSchedule} from '../../../model/class-schedule';
import {faEye} from '@fortawesome/free-solid-svg-icons';
import {TypeClass} from '../../../model/type-class';

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
  faEye = faEye;
  typeClass: TypeClass;


  constructor(private _loginService: LoginService, private _classDirectedService: ClassDirectedService,
              private _datePipe: DatePipe, private _route: Router, private _modalService: NgbModal) {
  }

  private static titleCaseWord(word: string) {
    if (!word) {
      return word;
    }
    return word[0].toUpperCase() + word.substr(1).toLowerCase();
  }

  ngOnInit() {
    this.user = this._loginService.getUser();

    const todayDayOfWeek = this.getTodayDayOfWeek();

    this.activeId = 'tab-' + todayDayOfWeek;
    this.setClasses(this.user.gym.id, todayDayOfWeek);

  }

  private getTodayDayOfWeek() {
    const todayDate = new Date();
    return ClassDirectedComponent.titleCaseWord(this._datePipe
      .transform(todayDate, 'EEEE', todayDate.getTimezoneOffset().toString(), 'es-ESP'));
  }

  public beforeChange($event: NgbTabChangeEvent) {
    const dayOfWeek = $event.nextId.substring(4);
    if (this.isNullList(dayOfWeek)) {
      this.setClasses(this.user.gym.id, dayOfWeek);
    }
  }

  public addClientToClass(classDirected: ClassDirected) {
    this._classDirectedService.reserveClass(classDirected, this.user.id).subscribe((res) => {
      if (res === true) {
        classDirected.clientList.push(this.user);
        alert('Ya tiene su plaza!');
      } else {
        alert('Algo fue mal');
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
    const todayDayOfWeek = this.getTodayDayOfWeek();
    return classSchedule.dayOfWeek === todayDayOfWeek;
  }

  private setClasses(gymId: number, dayOfWeek: string) {
    this._classDirectedService.getAllClassesOfGymAndDay(gymId, dayOfWeek).subscribe(res => {
      this.setClass(dayOfWeek, res);
    }, error => {
      console.log(error);
    });
  }

  isClient() {
    return this.user.role === 'socio';
  }

  isMyClass(assignedMonitorId) {
    return assignedMonitorId === this.user.id;
  }

  private setClass(dayOfWeek: string, classes: ClassDirected[]) {
    switch (dayOfWeek) {
      case 'Lunes':
        this.classesOfMon = classes;
        break;
      case 'Martes':
        this.classesOfTues = classes;
        break;
      case 'Miercoles':
        this.classesOfWed = classes;
        break;
      case 'Jueves':
        this.classesOfThur = classes;
        break;
      case 'Viernes':
        this.classesOfFri = classes;
        break;
      case 'Sábado':
        this.classesOfSat = classes;
        break;
      case 'Domingo':
        this.classesOfSun = classes;
    }
  }

  private isNullList(dayOfWeek: string) {
    switch (dayOfWeek) {
      case 'Lunes':
        if (this.classesOfMon !== null) {
          return false;
        }
        break;

      case 'Martes':
        if (this.classesOfTues !== null) {
          return false;
        }
        break;

      case 'Miercoles':
        if (this.classesOfWed !== null) {
          return false;
        }
        break;

      case 'Jueves':
        if (this.classesOfThur !== null) {
          return false;
        }
        break;

      case 'Viernes':
        if (this.classesOfFri !== null) {
          return false;
        }
        break;

      case 'Sábado':
        if (this.classesOfSat !== null) {
          return false;
        }
        break;

      case 'Domingo':
        if (this.classesOfSun !== null) {
          return false;
        }
    }
    return true;
  }

  open(content, typeClass: TypeClass) {
    this._modalService.open(content, {centered: true});
    this.typeClass = typeClass;
  }

  getNameOrNothing(assignedMonitor: User) {
    if (assignedMonitor) {
      return 'Asignada a ' + assignedMonitor.name;
    }
    return 'Aún no asignada';
  }
}
