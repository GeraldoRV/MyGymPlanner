import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../../service/login.service';
import {ClassDirectedService} from '../../../service/class-directed.service';
import {Gym} from '../../../model/gym';
import {ClassDirected} from '../../../model/class-directed';
import {NgbTabChangeEvent} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-class-directed',
  templateUrl: './class-directed.component.html',
  styleUrls: ['./class-directed.component.css']
})
export class ClassDirectedComponent implements OnInit {
  private gym: Gym;
  private classesOfMon: ClassDirected[] = null;
  private classesOfTues: ClassDirected[] = null;
  private classesOfWed: ClassDirected[] = null;
  private classesOfThur: ClassDirected[] = null;
  private classesOfFri: ClassDirected[] = null;
  private classesOfSat: ClassDirected[] = null;
  private classesOfSun: ClassDirected[] = null;

  constructor(private _loginService: LoginService, private _classDirectedService: ClassDirectedService) {
  }

  ngOnInit() {
    this.gym = this._loginService.getUser().gym;
    this._classDirectedService.getAllClassesOfGym(this.gym.id).subscribe(res => {
      this.classesOfMon = res;
    }, error => {
      console.log(error);
    });
  }

  public beforeChange($event: NgbTabChangeEvent) {
    const dayOfWeek = $event.nextId.substring(4);
    if (this.isNullList(dayOfWeek)) {
      this._classDirectedService.getAllClassesOfGymAndDay(1, dayOfWeek).subscribe(res => {
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
