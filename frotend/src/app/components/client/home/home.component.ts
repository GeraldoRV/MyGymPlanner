import {Component, OnInit, TemplateRef} from '@angular/core';
import {LoginService} from '../../../service/login.service';
import {Gym} from '../../../model/gym';
import {TypeClassService} from '../../../service/type-class.service';
import {TypeClass} from '../../../model/type-class';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  nameGym: string;
  directionGym: string;
  taughtClasses: TypeClass[];
  private gym: Gym;
  mondayToFriday: string;
  saturdays: string;
  sundaysAndHolidays: string;
  public typeClass: TypeClass;

  constructor(private _loginService: LoginService,
              private _typeClassService: TypeClassService, private _modalService: NgbModal) {
  }

  ngOnInit() {
    this.gym = this._loginService.getUser().gym;
    this.setGymDetails();
    this.getAllClasses();
  }

  private getAllClasses() {
    this._typeClassService.getAll().subscribe(res => {
      this.taughtClasses = res;
    }, error => {
      console.log(error);
    });

  }

  private setGymDetails() {
    this.nameGym = this.gym.name;
    this.directionGym = this.gym.direction;
    this.mondayToFriday = this.gym.openingHours.mondaysToFridays;
    this.saturdays = this.gym.openingHours.saturdays;
    this.sundaysAndHolidays = this.gym.openingHours.sundaysAndHolidays;
  }

  open(taughtClass: TypeClass, content: TemplateRef<any>) {
    this._modalService.open(content, {centered: true});
    this.typeClass = taughtClass;
  }
}
