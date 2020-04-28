import {Component, Input, OnInit} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {faTimes} from '@fortawesome/free-solid-svg-icons/faTimes';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Team} from '../../../../model/team';
import {TeamService} from '../../../../service/team.service';

@Component({
  selector: 'app-add-team',
  templateUrl: './add-team.component.html',
  styleUrls: ['./add-team.component.css']
})
export class AddTeamComponent implements OnInit {
  @Input() typeClass;
  faTimes = faTimes;
  assignTeamForm: FormGroup;
  teams: Team[];

  constructor(public activeModal: NgbActiveModal, private fb: FormBuilder, private teamService: TeamService) {
  }

  ngOnInit() {
    this.assignTeamForm = this.fb.group({
      team: []
    });
    this.teamService.getAllTeams().subscribe(teams => {
      this.teams = teams;
    }, error => {
      console.log(error);
    });
  }

  assignTeam() {
    console.log(this.assignTeamForm.value);
    console.log(this.typeClass);
  }
}
