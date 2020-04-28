import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import {AppComponent} from './app.component';
import {LoginComponent} from './components/login/login.component';
import {RouterModule, Routes} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {LoginService} from './service/login.service';
import {HomeComponent} from './components/client/home/home.component';
import {MainComponent, NgbdSortableHeader} from './components/admin/main/main.component';
import {UserService} from './service/user.service';
import {NewUserComponent} from './components/admin/new-user/new-user.component';
import {AlertComponent} from './components/alert/alert.component';
import {NavbarComponent} from './components/navbar/navbar.component';
import {TablesComponent} from './components/client/tables/tables.component';
import {WorkoutTableService} from './service/workout-table.service';
import {TableComponent} from './components/client/table/table.component';
import {AuthGuard} from './service/authentication/auth.guard';
import {NotFoundComponent} from './components/not-found/not-found.component';
import {ClassDirectedComponent} from './components/class-directed/class-directed.component';
import {DatePipe} from '@angular/common';
import {MainMonitorComponent} from './components/monitor/main-monitor/main-monitor.component';
import {ExerciseTypeService} from './service/exercise-type.service';
import {Global} from './utilities/global';
import {AssignedClassComponent} from './components/monitor/assigned-class/assigned-class.component';
import {EvenOrOdd} from './utilities/even-or-odd';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {ExerciseDetailsComponent} from './components/client/exercise-details/exercise-details.component';
import {TeamsListComponent} from './components/admin/teams-list/teams-list.component';
import {NewTeamComponent} from './components/admin/new-team/new-team.component';
import {MyTeamComponent} from './components/leader/my-team/my-team.component';
import {ClassListComponent} from './components/admin/class-list/class-list.component';
import {AddTeamComponent} from './components/admin/class-list/add-team/add-team.component';

const appRoutes: Routes = [
  {path: '', component: LoginComponent},
  {
    path: 'client', component: HomeComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'admin', component: MainComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'admin/class-list', component: ClassListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'new-user', component: NewUserComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'teams', component: TeamsListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'new-team', component: NewTeamComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'routines', component: TablesComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'routine', component: TableComponent,
    canActivate: [AuthGuard]
  }, {
    path: 'exercise', component: ExerciseDetailsComponent,
    canActivate: [AuthGuard]
  }, {
    path: 'classes', component: ClassDirectedComponent,
    canActivate: [AuthGuard]
  }, {
    path: 'assigned-class', component: AssignedClassComponent,
    canActivate: [AuthGuard]
  }, {
    path: 'assigned-classes', component: MyTeamComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'monitor', component: MainMonitorComponent,
    canActivate: [AuthGuard]
  },
  {path: '**', component: NotFoundComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    MainComponent,
    NewUserComponent,
    AlertComponent,
    NgbdSortableHeader,
    NavbarComponent,
    TablesComponent,
    TableComponent,
    NotFoundComponent,
    ClassDirectedComponent,
    MainMonitorComponent,
    AssignedClassComponent,
    EvenOrOdd,
    ExerciseDetailsComponent,
    TeamsListComponent,
    NewTeamComponent,
    MyTeamComponent,
    ClassListComponent,
    AddTeamComponent
  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    HttpClientModule,
    NgbModule,
    FontAwesomeModule,
    RouterModule.forRoot(appRoutes, {useHash: true})
  ],
  entryComponents: [AddTeamComponent],
  providers: [LoginService, UserService, WorkoutTableService, ExerciseTypeService,
    AuthGuard, DatePipe, Global],
  bootstrap: [AppComponent]
})
export class AppModule {
}
