import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import {AppComponent} from './app.component';
import {LoginComponent} from './components/login/login.component';
import {RouterModule, Routes} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {LoginService} from './service/login.service';
import {HomeComponent} from './components/home/home.component';
import {MainComponent, NgbdSortableHeader} from './components/admin/main/main.component';
import {UserService} from './service/user.service';
import {NewUserComponent} from './components/admin/new-user/new-user.component';
import {AlertComponent} from './components/alert/alert.component';
import {NavbarComponent} from './components/navbar/navbar.component';
import {TablesComponent} from './components/tables/tables.component';
import {WorkoutTableService} from './service/workout-table.service';
import {TableComponent} from './components/table/table.component';
import {AuthGuard} from './service/authentication/auth.guard';

const appRoutes: Routes = [
  {path: '', component: LoginComponent},
  {
    path: 'home', component: HomeComponent,
    canActivate: [AuthGuard]
  },
  {path: 'admin', component: MainComponent,
    canActivate: [AuthGuard]},
  {path: 'new-user', component: NewUserComponent,
    canActivate: [AuthGuard]},
  {path: 'routines', component: TablesComponent,
    canActivate: [AuthGuard]},
  {path: 'routine', component: TableComponent,
    canActivate: [AuthGuard]}
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
    TableComponent
  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    HttpClientModule,
    NgbModule,
    RouterModule.forRoot(appRoutes, {useHash: true})
  ],
  providers: [LoginService, UserService, WorkoutTableService, AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule {
}
