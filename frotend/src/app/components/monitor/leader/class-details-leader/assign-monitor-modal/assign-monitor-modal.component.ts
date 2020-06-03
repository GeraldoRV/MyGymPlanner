import {Component, Input, OnInit} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {faTimes} from '@fortawesome/free-solid-svg-icons/faTimes';
import {UserTypeMonitorDto} from '../../../../../dto/user-type-monitor.dto';
import {UserService} from '../../../../../service/user.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ClassDirectedService} from '../../../../../service/class-directed.service';

@Component({
  selector: 'app-assign-monitor-modal',
  templateUrl: './assign-monitor-modal.component.html',
  styleUrls: ['./assign-monitor-modal.component.css']
})
export class AssignMonitorModalComponent implements OnInit {
  @Input() leaderId: number;
  @Input() classDirectedId: number;
  faTimes = faTimes;
  monitors: UserTypeMonitorDto[];
  monitorAssignForm: FormGroup;

  constructor(public activeModal: NgbActiveModal, private userService: UserService, private fb: FormBuilder,
              private classDirectedService: ClassDirectedService) {
  }

  ngOnInit() {
    this.monitorAssignForm = this.fb.group(
      {
        monitor: []
      }
    );
    this.userService.getAllMonitorsOfTeamLeaderId(this.leaderId).subscribe(res => {
      this.monitors = res;
    }, error => console.log(error));

  }

  onSubmit() {
    this.classDirectedService
      .assignMonitor(this.classDirectedId, this.monitorAssignForm.controls.monitor.value)
      .subscribe(res => {
        console.log(res);
      }, error => console.log(error));
  }
}
