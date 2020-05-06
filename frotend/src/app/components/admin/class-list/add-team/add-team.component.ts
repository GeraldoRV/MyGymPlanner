import {Component, Input, OnInit} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {faTimes} from '@fortawesome/free-solid-svg-icons/faTimes';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Team} from '../../../../model/team';
import {TeamService} from '../../../../service/team.service';
import {TypeClassService} from '../../../../service/type-class.service';
import {TypeClassAdminDto} from '../../../../dto/type-class-admin.dto';

@Component({
  selector: 'app-add-team',
  templateUrl: './add-team.component.html',
  styleUrls: ['./add-team.component.css']
})
export class AddTeamComponent implements OnInit {
  @Input() typeClassId;
  faTimes = faTimes;
  assignTeamForm: FormGroup;
  teams: Team[];

  constructor(public activeModal: NgbActiveModal, private fb: FormBuilder, private teamService: TeamService,
              private typeClassService: TypeClassService) {
  }

  ngOnInit() {
    this.assignTeamForm = this.fb.group({
      teamId: []
    });
    this.teamService.getAllTeams().subscribe(teams => {
      this.teams = teams;
    }, error => {
      console.log(error);
    });
  }

  assignTeam() {
    const typeClassAdminDto = new TypeClassAdminDto();
    typeClassAdminDto.id = this.typeClassId;
    this.typeClassService.addTeam(typeClassAdminDto, this.assignTeamForm.value.teamId).subscribe(typeClass => {
      this.activeModal.close(typeClass);
    }, error => console.log(error));

  }
}
