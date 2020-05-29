import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {MonitorRoutingModule} from './monitor-routing.module';
import {MainMonitorComponent} from './main-monitor/main-monitor.component';
import {AssignedClassComponent} from './assigned-class/assigned-class.component';
import {MyTeamComponent} from '../leader/my-team/my-team.component';
import {CommonComponentsModule} from '../commons/common-components.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {UserService} from '../../service/user.service';
import {TeamService} from '../../service/team.service';

@NgModule({
  declarations: [
    MainMonitorComponent,
    AssignedClassComponent,
    MyTeamComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    HttpClientModule,
    FontAwesomeModule,
    CommonComponentsModule,
    MonitorRoutingModule
  ],
  providers: [UserService, TeamService]
})
export class MonitorModule {
}
