import {Component, Input, OnInit} from '@angular/core';
import {Team} from '../../../../model/team';
import {TypeClassService} from '../../../../service/type-class.service';
import {TypeClassLeaderDto} from '../../../../dto/type-class-leader.dto';

@Component({
  selector: 'app-assigned-classes',
  templateUrl: './assigned-classes.component.html',
  styleUrls: ['./assigned-classes.component.css']
})
export class AssignedClassesComponent implements OnInit {
  @Input() team: Team;
  classes: TypeClassLeaderDto[];

  constructor(private typeClassService: TypeClassService) {
  }

  ngOnInit() {
    this.typeClassService.getAllClassesForLeader(this.team.id).subscribe(res => {
      this.classes = res;
    }, error => console.log(error));
  }

}
