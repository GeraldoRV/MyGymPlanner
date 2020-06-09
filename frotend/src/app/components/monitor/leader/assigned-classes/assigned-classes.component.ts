import {Component, Input, OnInit} from '@angular/core';
import {TypeClassService} from '../../../../service/type-class.service';
import {TypeClassLeaderDto} from '../../../../dto/type-class-leader.dto';
import {Router} from '@angular/router';
import {TeamDto} from '../../../../dto/team.dto';

@Component({
  selector: 'app-assigned-classes',
  templateUrl: './assigned-classes.component.html',
  styleUrls: ['./assigned-classes.component.css']
})
export class AssignedClassesComponent implements OnInit {
  @Input() team: TeamDto;
  classes: TypeClassLeaderDto[];

  constructor(private typeClassService: TypeClassService, private  route: Router) {
  }

  ngOnInit() {
    this.typeClassService.getAllClassesForLeader(this.team.id).subscribe(res => {
      this.classes = res;
    }, error => console.log(error));
  }

  seeClass(typeClass: TypeClassLeaderDto) {
    this.route.navigate(['monitor/class', typeClass.id, typeClass.name]);

  }
}
