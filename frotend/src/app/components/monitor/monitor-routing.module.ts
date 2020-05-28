import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AssignedClassComponent} from './assigned-class/assigned-class.component';
import {AuthGuard} from '../../service/authentication/auth.guard';
import {MainMonitorComponent} from './main-monitor/main-monitor.component';
import {MyTeamComponent} from './leader/my-team/my-team.component';
import {ClassDetailsLeaderComponent} from './leader/class-details-leader/class-details-leader.component';

const routes: Routes = [
  {
    path: 'monitor/assigned-class', component: AssignedClassComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'monitor/assigned-classes', component: MyTeamComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'monitor/class/:id', component: ClassDetailsLeaderComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'monitor', component: MainMonitorComponent,
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MonitorRoutingModule {
}
