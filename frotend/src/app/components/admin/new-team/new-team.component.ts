import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-new-team',
  templateUrl: './new-team.component.html',
  styleUrls: ['./new-team.component.css']
})
export class NewTeamComponent implements OnInit {
  teamForm: FormGroup;

  constructor(private fb: FormBuilder) {
  }

  ngOnInit() {
    this.teamForm = this.fb.group(
      {
        teamName: [''],
        leader: [''],
        monitors: [''],
        members: ['']
      }
    );
  }

}
