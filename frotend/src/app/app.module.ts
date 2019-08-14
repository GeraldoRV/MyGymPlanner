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
import { AlertComponent } from './components/alert/alert.component';
import {AlertService} from './service/alert.service';
import { NavbarComponent } from './components/navbar/navbar.component';

const appRoutes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'home', component: HomeComponent},
  {
    path: 'admin', component: MainComponent,
    children: [{path: 'add-user', component: NewUserComponent}]
  }
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
    NavbarComponent
  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    HttpClientModule,
    NgbModule,
    RouterModule.forRoot(appRoutes, {useHash: true})
  ],
  providers: [LoginService, GymService, UserService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
