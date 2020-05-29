import {NgModule} from '@angular/core';

import {AdminRoutingModule} from './admin-routing.module';
import {MainComponent, NgbdSortableHeader} from './main/main.component';
import {NewUserComponent} from './new-user/new-user.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';
import {TeamsListComponent} from './teams-list/teams-list.component';
import {NewTeamComponent} from './new-team/new-team.component';
import {ClassListComponent} from './class-list/class-list.component';
import {HttpClientModule} from '@angular/common/http';
import {AddTeamComponent} from './class-list/add-team/add-team.component';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {UserService} from '../../service/user.service';
import {TeamService} from '../../service/team.service';
import {TypeClassService} from '../../service/type-class.service';

@NgModule({
  declarations: [
    MainComponent,
    NewUserComponent,
    NgbdSortableHeader,
    TeamsListComponent,
    NewTeamComponent,
    ClassListComponent,
    AddTeamComponent

  ],
  imports: [
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    FontAwesomeModule,
    BrowserModule,
    AdminRoutingModule
  ],
  entryComponents: [AddTeamComponent],
  providers: [UserService, TeamService, TypeClassService]

})
export class AdminModule {
}
