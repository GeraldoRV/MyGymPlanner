import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {LoginComponent} from './components/login/login.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {LoginService} from './service/login.service';
import {NavbarComponent} from './components/navbar/navbar.component';
import {AuthGuard} from './service/authentication/auth.guard';
import {NotFoundComponent} from './components/not-found/not-found.component';
import {DatePipe} from '@angular/common';
import {Global} from './utilities/global';
import {AppRoutingModule} from './app-routing.module';
import {AdminModule} from './components/admin/admin.module';
import {ClientModule} from './components/client/client.module';
import {MonitorModule} from './components/monitor/monitor.module';
import {CommonComponentsModule} from './components/commons/common-components.module';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatMenuModule} from '@angular/material/menu';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NavbarComponent,
    NotFoundComponent,

  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    HttpClientModule,
    CommonComponentsModule,
    AdminModule,
    ClientModule,
    MonitorModule,
    AppRoutingModule,
    FontAwesomeModule,
    MatIconModule,
    MatButtonModule,
    MatMenuModule,
  ],
  providers: [LoginService,
    AuthGuard, DatePipe, Global],
  bootstrap: [AppComponent]
})
export class AppModule {
}
