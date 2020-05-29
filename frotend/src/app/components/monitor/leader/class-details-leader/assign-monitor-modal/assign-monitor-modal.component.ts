import { Component, OnInit } from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {faTimes} from '@fortawesome/free-solid-svg-icons/faTimes';
import {ClassDirectedToAssignDto} from '../../../../../dto/class-directed-to-assign.dto';
import {User} from '../../../../../model/user';
import {Team} from '../../../../../model/team';

@Component({
  selector: 'app-assign-monitor-modal',
  templateUrl: './assign-monitor-modal.component.html',
  styleUrls: ['./assign-monitor-modal.component.css']
})
export class AssignMonitorModalComponent implements OnInit {
  faTimes = faTimes;
  classDirected: ClassDirectedToAssignDto;
  monitors: User[];
  team: Team;
  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit() {
  }

}
