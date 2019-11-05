import {Injectable} from '@angular/core';
import {AlertComponent} from '../components/alert/alert.component';

@Injectable({
  providedIn: 'root'
})
export class AlertService {
  private alert: AlertComponent = null;

  constructor() {
  }

  register(alert: AlertComponent) {
    this.alert = alert;
  }

  setType(type) {
    this.alert.setType(type);
  }

  setMessage(message) {
    this.alert.setMessage(message);
  }

  setDismissible(dismissible) {
    this.alert.setDismissible(dismissible);
  }

  setTimeout(time: number) {
    this.alert.setTimeout(time);
  }

  show() {
    this.alert.show();
  }
}
