import { Component, OnInit } from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {faTimes} from '@fortawesome/free-solid-svg-icons/faTimes';

@Component({
  selector: 'app-assign-monitor-modal',
  templateUrl: './assign-monitor-modal.component.html',
  styleUrls: ['./assign-monitor-modal.component.css']
})
export class AssignMonitorModalComponent implements OnInit {
  faTimes = faTimes;
  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit() {
  }

}
