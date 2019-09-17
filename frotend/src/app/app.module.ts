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
import {GymService} from './service/gym.service';
import {MainComponent, NgbdSortableHeader} from './components/admin/main/main.component';
import {UserService} from './service/user.service';
import {NewUserComponent} from './components/admin/new-user/new-user.component';
import {AlertComponent} from './components/alert/alert.component';
import {AlertService} from './service/alert.service';
import {NavbarComponent} from './components/navbar/navbar.component';
import {TablesComponent} from './components/tables/tables.component';
import {WorkoutTableService} from './service/workout-table.service';
import { TableComponent} from './components/table/table.component';

const appRoutes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'home', component: HomeComponent},
  {
    path: 'admin', component: MainComponent,
    children: [{path: 'add-user', component: NewUserComponent}]
  },
  {path: 'routines', component: TablesComponent},
  {path: 'routine', component: TableComponent}
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
  providers: [LoginService, GymService, UserService, WorkoutTableService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
