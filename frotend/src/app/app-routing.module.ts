import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {AuthGuard} from './service/authentication/auth.guard';
import {AssignedClassComponent} from './components/monitor/assigned-class/assigned-class.component';
import {MyTeamComponent} from './components/leader/my-team/my-team.component';
import {MainMonitorComponent} from './components/monitor/main-monitor/main-monitor.component';
import {NotFoundComponent} from './components/not-found/not-found.component';

const appRoutes: Routes = [
  {path: '', component: LoginComponent},
  {
    path: 'assigned-class', component: AssignedClassComponent,
    canActivate: [AuthGuard]
  }, {
    path: 'assigned-classes', component: MyTeamComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'monitor', component: MainMonitorComponent,
    canActivate: [AuthGuard]
  },
  {path: '**', component: NotFoundComponent}
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(
      appRoutes,
      {useHash: true}
    )
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
