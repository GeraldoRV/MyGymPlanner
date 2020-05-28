import {Component, Input, OnInit} from '@angular/core';
import {Team} from '../../../../model/team';
import {TypeClassService} from '../../../../service/type-class.service';
import {TypeClassLeaderDto} from '../../../../dto/type-class-leader.dto';
import {Router} from '@angular/router';

@Component({
  selector: 'app-assigned-classes',
  templateUrl: './assigned-classes.component.html',
  styleUrls: ['./assigned-classes.component.css']
})
export class AssignedClassesComponent implements OnInit {
  @Input() team: Team;
  classes: TypeClassLeaderDto[];

  constructor(private typeClassService: TypeClassService, private  route: Router) {
  }

  ngOnInit() {
    this.typeClassService.getAllClassesForLeader(this.team.id).subscribe(res => {
      this.classes = res;
    }, error => console.log(error));
  }

  seeClass(id: number) {
    this.route.navigate(['monitor/class', id]);

  }
}
