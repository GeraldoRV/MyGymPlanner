import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {MainComponent} from './main/main.component';
import {AuthGuard} from '../../service/authentication/auth.guard';
import {NewUserComponent} from './new-user/new-user.component';
import {TeamsListComponent} from './teams-list/teams-list.component';
import {NewTeamComponent} from './new-team/new-team.component';
import {ClassListComponent} from './class-list/class-list.component';

const routes: Routes = [
  {
    path: 'admin', component: MainComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'admin/new-user', component: NewUserComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'admin/teams', component: TeamsListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'admin/new-team', component: NewTeamComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'admin/class-list', component: ClassListComponent,
    canActivate: [AuthGuard]
  }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {
}
