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
import { AssignedClassesComponent } from './leader/assigned-classes/assigned-classes.component';
import { ClassDetailsLeaderComponent } from './leader/class-details-leader/class-details-leader.component';

@NgModule({
  declarations: [
    MainMonitorComponent,
    AssignedClassComponent,
    MyTeamComponent,
    AssignedClassesComponent,
    ClassDetailsLeaderComponent
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
  ]
})
export class MonitorModule {
}
