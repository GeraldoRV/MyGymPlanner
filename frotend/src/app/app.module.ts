import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import {AppComponent} from './app.component';
import {LoginComponent} from './components/login/login.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {LoginService} from './service/login.service';
import {NgbdSortableHeader} from './components/admin/main/main.component';
import {UserService} from './service/user.service';
import {NavbarComponent} from './components/navbar/navbar.component';
import {WorkoutTableService} from './service/workout-table.service';
import {AuthGuard} from './service/authentication/auth.guard';
import {NotFoundComponent} from './components/not-found/not-found.component';
import {DatePipe} from '@angular/common';
import {MainMonitorComponent} from './components/monitor/main-monitor/main-monitor.component';
import {ExerciseTypeService} from './service/exercise-type.service';
import {Global} from './utilities/global';
import {AssignedClassComponent} from './components/monitor/assigned-class/assigned-class.component';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {MyTeamComponent} from './components/leader/my-team/my-team.component';
import {AddTeamComponent} from './components/admin/class-list/add-team/add-team.component';
import {AppRoutingModule} from './app-routing.module';
import {AdminModule} from './components/admin/admin.module';
import {ClientModule} from './components/client/client.module';
import {MonitorModule} from './components/monitor/monitor.module';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NgbdSortableHeader,
    NavbarComponent,
    NotFoundComponent,
    MainMonitorComponent,
    AssignedClassComponent,
    MyTeamComponent,
    AddTeamComponent
  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    HttpClientModule,
    NgbModule,
    FontAwesomeModule,
    AdminModule,
    ClientModule,
    MonitorModule,
    AppRoutingModule
  ],
  entryComponents: [AddTeamComponent],
  providers: [LoginService, UserService, WorkoutTableService, ExerciseTypeService,
    AuthGuard, DatePipe, Global],
  bootstrap: [AppComponent]
})
export class AppModule {
}
