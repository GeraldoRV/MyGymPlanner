import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ClassDirectedService} from '../../../../service/class-directed.service';
import {ClassDirectedToAssignDto} from '../../../../dto/class-directed-to-assign.dto';

@Component({
  selector: 'app-class-details-leader',
  templateUrl: './class-details-leader.component.html',
  styleUrls: ['./class-details-leader.component.css']
})
export class ClassDetailsLeaderComponent implements OnInit {
  classes: ClassDirectedToAssignDto[];

  constructor(private router: Router, private route: ActivatedRoute, private classDirectedService: ClassDirectedService) {
  }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.classDirectedService.getAllClassesOfTypeClass(id).subscribe(res => {
      this.classes = res;
    }, error => console.log(error));

  }

}
