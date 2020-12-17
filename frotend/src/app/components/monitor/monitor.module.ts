import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {MonitorRoutingModule} from './monitor-routing.module';
import {MainMonitorComponent} from './main-monitor/main-monitor.component';
import {AssignedClassComponent} from './assigned-class/assigned-class.component';
import {MyTeamComponent} from './leader/my-team/my-team.component';
import {CommonComponentsModule} from '../commons/common-components.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {AssignedClassesComponent} from './leader/assigned-classes/assigned-classes.component';
import {ClassDetailsLeaderComponent} from './leader/class-details-leader/class-details-leader.component';
import {UserService} from '../../service/user.service';
import {TeamService} from '../../service/team.service';
import {AssignMonitorModalComponent} from './leader/class-details-leader/assign-monitor-modal/assign-monitor-modal.component';
import {NgbModalModule} from '@ng-bootstrap/ng-bootstrap';
import {MatTableModule} from '@angular/material/table';
import {MatSortModule} from '@angular/material/sort';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';

@NgModule({
  declarations: [
    MainMonitorComponent,
    AssignedClassComponent,
    MyTeamComponent,
    AssignedClassesComponent,
    ClassDetailsLeaderComponent,
    AssignMonitorModalComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    HttpClientModule,
    FontAwesomeModule,
    NgbModalModule,
    CommonComponentsModule,
    MonitorRoutingModule,
    MatTableModule,
    MatSortModule,
    MatButtonModule,
    MatIconModule,
    MatListModule
  ],
  entryComponents: [AssignMonitorModalComponent],
  providers: [UserService, TeamService]
})
export class MonitorModule {
}
