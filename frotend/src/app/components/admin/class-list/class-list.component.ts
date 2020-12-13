import {Component, HostListener, OnInit, ViewChild} from '@angular/core';
import {LoginService} from '../../../service/login.service';
import {TypeClassService} from '../../../service/type-class.service';
import {TypeClassAdminDto} from '../../../dto/type-class-admin.dto';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {AddTeamComponent} from './add-team/add-team.component';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-class-list',
  templateUrl: './class-list.component.html',
  styleUrls: ['./class-list.component.css']
})
export class ClassListComponent implements OnInit {
  classes: TypeClassAdminDto[];
  dataSource = null;
  @ViewChild(MatSort) sort: MatSort;
  displayedColumns: string[] = ['name', 'nClassesDirected', 'team.name', 'options'];
  isColumnsMobile = false;
  private displayedColumnForMobilesPhones: string[] = ['name', 'nClassesDirected', 'team.name'];
  private initialDisplayedColumns: string[] = ['name', 'nClassesDirected', 'team.name', 'options'];

  constructor(private logUser: LoginService, private typeClassService: TypeClassService, private modalRef: NgbModal) {
  }

  ngOnInit() {
    this.onResize();

    this.typeClassService.getAllTaughtClass().subscribe(res => {
      this.classes = res;
      this.dataSource = new MatTableDataSource(this.classes);
      setTimeout(() => this.dataSource.sort = this.sort);
    }, error => console.log(error));
  }

  openModal(typeClass: TypeClassAdminDto) {
    const modalRef = this.modalRef.open(AddTeamComponent, {centered: true});
    modalRef.componentInstance.typeClass = typeClass;
    modalRef.result.then(result => {
      if (result !== 'Close click') {
        const typeClassAdminDto = this.classes.find(typeClassDto => typeClassDto.id === typeClass.id);
        typeClassAdminDto.team = result.team;
      }
    })
      .catch(() => console.log('Cross click'));
  }

  @HostListener('window:resize')
  onResize() {

    if (window.screen.width < 740 && !this.isColumnsMobile) {

      this.displayedColumns = this.displayedColumnForMobilesPhones;
      this.isColumnsMobile = true;

    } else if (window.screen.width >= 740 && this.isColumnsMobile) {

      this.displayedColumns = this.initialDisplayedColumns;
      this.isColumnsMobile = false;

    }

  }

  getNameButton(typeClass: TypeClassAdminDto) {
    if (typeClass.team) {
      return 'Modificar';
    }
    return 'Añadir';
  }
}
