import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../../service/login.service';
import {TypeClassService} from '../../../service/type-class.service';
import {TypeClassAdminDto} from '../../../dto/type-class-admin.dto';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {AddTeamComponent} from './add-team/add-team.component';

@Component({
  selector: 'app-class-list',
  templateUrl: './class-list.component.html',
  styleUrls: ['./class-list.component.css']
})
export class ClassListComponent implements OnInit {
  classes: TypeClassAdminDto[];

  constructor(private logUser: LoginService, private typeClassService: TypeClassService, private modalRef: NgbModal) {
  }

  ngOnInit() {
    this.typeClassService.getAllTaughtClass().subscribe(res => {
      this.classes = res;
    }, error => console.log(error));
  }

  openModal(typeClassId: number) {
    const modalRef = this.modalRef.open(AddTeamComponent);
    modalRef.componentInstance.typeClassId = typeClassId;
    modalRef.result.then(result => {
      if (result !== 'Cross Click') {
        const typeClassAdminDto = this.classes.find(typeClass => typeClass.id === typeClassId);
        typeClassAdminDto.team = result.team;
      }
    });
  }
}
