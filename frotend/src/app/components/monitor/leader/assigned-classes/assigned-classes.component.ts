import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {TypeClassService} from '../../../../service/type-class.service';
import {TypeClassLeaderDto} from '../../../../dto/type-class-leader.dto';
import {Router} from '@angular/router';
import {TeamDto} from '../../../../dto/team.dto';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {faEye} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-assigned-classes',
  templateUrl: './assigned-classes.component.html',
  styleUrls: ['./assigned-classes.component.css']
})
export class AssignedClassesComponent implements OnInit {
  @Input() team: TeamDto;
  classes: TypeClassLeaderDto[];
  dataSource = null;
  @ViewChild(MatSort) sort: MatSort;
  displayedColumns: string[] = ['name', 'nClassesDirected', 'nClassesDirectedWithMonitor', 'options'];
  faEye = faEye;


  constructor(private typeClassService: TypeClassService, private  route: Router) {
  }

  ngOnInit() {
    sessionStorage.removeItem('rollback');
    this.typeClassService.getAllClassesForLeader(this.team.id).subscribe(res => {
      this.classes = res;
      this.dataSource = new MatTableDataSource(this.classes);
      setTimeout(() => this.dataSource.sort = this.sort);
    }, error => console.log(error));
  }

  seeClass(typeClass: TypeClassLeaderDto) {
    this.route.navigate(['monitor/class', typeClass.id, typeClass.name]);

  }
}
