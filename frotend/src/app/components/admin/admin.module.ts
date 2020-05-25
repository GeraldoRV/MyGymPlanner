import {NgModule} from '@angular/core';

import {AdminRoutingModule} from './admin-routing.module';
import {MainComponent} from './main/main.component';
import {NewUserComponent} from './new-user/new-user.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';
import {TeamsListComponent} from './teams-list/teams-list.component';
import {NewTeamComponent} from './new-team/new-team.component';
import {ClassListComponent} from './class-list/class-list.component';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
  declarations: [
    MainComponent,
    NewUserComponent,
    TeamsListComponent,
    NewTeamComponent,
    ClassListComponent
  ],
  imports: [
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    AdminRoutingModule
  ]
})
export class AdminModule {
}
