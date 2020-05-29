import {Component, Input, OnInit} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {faTimes} from '@fortawesome/free-solid-svg-icons/faTimes';
import {ClassDirectedToAssignDto} from '../../../../../dto/class-directed-to-assign.dto';
import {UserTypeMonitorDto} from '../../../../../dto/user-type-monitor.dto';
import {UserService} from '../../../../../service/user.service';

@Component({
  selector: 'app-assign-monitor-modal',
  templateUrl: './assign-monitor-modal.component.html',
  styleUrls: ['./assign-monitor-modal.component.css']
})
export class AssignMonitorModalComponent implements OnInit {
  faTimes = faTimes;
  classDirected: ClassDirectedToAssignDto;
  monitors: UserTypeMonitorDto[];
  @Input() leaderId: number;

  constructor(public activeModal: NgbActiveModal, private userService: UserService) {
  }

  ngOnInit() {
    this.userService.getAllMonitorsOfTeamLeaderId(this.leaderId).subscribe(res => {
      this.monitors = res;
    }, error => console.log(error));

  }

}
