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
import {ExerciseTypeService} from './service/exercise-type.service';
import {Global} from './utilities/global';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {AddTeamComponent} from './components/admin/class-list/add-team/add-team.component';
import {AppRoutingModule} from './app-routing.module';
import {AdminModule} from './components/admin/admin.module';
import {ClientModule} from './components/client/client.module';
import {MonitorModule} from './components/monitor/monitor.module';
import {CommonComponentsModule} from './components/commons/common-components.module';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NgbdSortableHeader,
    NavbarComponent,
    NotFoundComponent,

    AddTeamComponent
  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    HttpClientModule,
    NgbModule,
    FontAwesomeModule,
    CommonComponentsModule,
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
