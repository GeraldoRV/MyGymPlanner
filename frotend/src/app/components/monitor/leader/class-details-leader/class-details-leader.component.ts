import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ClassDirectedService} from '../../../../service/class-directed.service';
import {ClassDirectedToAssignDto} from '../../../../dto/class-directed-to-assign.dto';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {AssignMonitorModalComponent} from './assign-monitor-modal/assign-monitor-modal.component';
import {LoginService} from '../../../../service/login.service';
import {User} from '../../../../model/user';

@Component({
  selector: 'app-class-details-leader',
  templateUrl: './class-details-leader.component.html',
  styleUrls: ['./class-details-leader.component.css']
})
export class ClassDetailsLeaderComponent implements OnInit {
  classes: ClassDirectedToAssignDto[];
  private userLogged: User;

  constructor(private router: Router, private route: ActivatedRoute,
              private classDirectedService: ClassDirectedService, private modalService: NgbModal,
              private loginService: LoginService) {
  }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.userLogged = this.loginService.getUser();
    this.classDirectedService.getAllClassesOfTypeClass(id).subscribe(res => {
      this.classes = res;
    }, error => console.log(error));

  }

  open(classDirectedId) {
    const modalRef = this.modalService.open(AssignMonitorModalComponent);
    modalRef.componentInstance.leaderId = this.userLogged.id;
    modalRef.componentInstance.classDirectedId = classDirectedId;
  }
}
