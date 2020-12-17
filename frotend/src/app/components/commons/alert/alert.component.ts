import {Component, ElementRef, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {AlertService} from '../../../service/alert.service';

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class AlertComponent implements OnInit {
  @Input() handlerId: string;
  type: string;
  message: string;
  dismissible: boolean;
  private element: any;

  constructor(private alertService: AlertService, private el: ElementRef) {
    this.element = el.nativeElement;
  }

  ngOnInit() {
    this.element.style.display = 'none';
    this.alertService.register(this);
  }

  setType(type) {
    this.type = type;
  }

  setMessage(message) {
    this.message = message;
  }

  setDismissible(dismissible) {
    this.dismissible = dismissible;
  }

  setTimeout(time: number) {
    setTimeout(() => this.element.style.display = 'none', time);
  }

  show() {
    this.element.style.display = 'block';
  }
}
