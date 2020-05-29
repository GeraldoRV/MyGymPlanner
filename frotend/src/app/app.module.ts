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
    AppRoutingModule
  ],
  providers: [LoginService,
    AuthGuard, DatePipe, Global],
  bootstrap: [AppComponent]
})
export class AppModule {
}
