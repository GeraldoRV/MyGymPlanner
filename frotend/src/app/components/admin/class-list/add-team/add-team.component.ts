import {Component, Input, OnInit} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {faTimes} from '@fortawesome/free-solid-svg-icons/faTimes';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
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
  @Input() typeClass: TypeClassAdminDto;
  faTimes = faTimes;
  assignTeamForm: FormGroup;
  teams: Team[] = null;
  submit = false;

  constructor(public activeModal: NgbActiveModal, private fb: FormBuilder, private teamService: TeamService,
              private typeClassService: TypeClassService) {
  }

  ngOnInit() {
    this.assignTeamForm = this.fb.group({
      teamId: [null, Validators.required]
    });
    this.teamService.getAllTeams().subscribe(teams => {
      this.teams = teams;
    }, error => {
      console.log(error);
    });
  }

  assignTeam() {
    if (!this.assignTeamForm.valid) {
      this.submit = true;
      return;
    }

    this.typeClassService.addTeam(this.typeClass, this.assignTeamForm.value.teamId).subscribe(typeClass => {
      this.activeModal.close(typeClass);
    }, error => console.log(error));

  }

  get controls() {
    return this.assignTeamForm.controls;
  }
}
