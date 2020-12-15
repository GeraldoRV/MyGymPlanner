import {NgModule} from '@angular/core';

import {AdminRoutingModule} from './admin-routing.module';
import {MainComponent} from './main/main.component';
import {NewUserComponent} from './new-user/new-user.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {TeamsListComponent} from './teams-list/teams-list.component';
import {NewTeamComponent} from './new-team/new-team.component';
import {ClassListComponent} from './class-list/class-list.component';
import {HttpClientModule} from '@angular/common/http';
import {AddTeamComponent} from './class-list/add-team/add-team.component';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {UserService} from '../../service/user.service';
import {TeamService} from '../../service/team.service';
import {TypeClassService} from '../../service/type-class.service';
import {MatTableModule} from '@angular/material/table';
import {MatSortModule} from '@angular/material/sort';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatIconModule} from '@angular/material/icon';

@NgModule({
  declarations: [
    MainComponent,
    NewUserComponent,
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
    BrowserAnimationsModule,
    AdminRoutingModule,
    MatTableModule,
    MatSortModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonToggleModule,
    MatIconModule
  ],
  entryComponents: [AddTeamComponent],
  providers: [UserService, TeamService, TypeClassService]

})
export class AdminModule {
}
