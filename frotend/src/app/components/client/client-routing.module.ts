import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {AuthGuard} from '../../service/authentication/auth.guard';
import {TablesComponent} from './tables/tables.component';
import {TableComponent} from './table/table.component';
import {ExerciseDetailsComponent} from './exercise-details/exercise-details.component';
import {ClassDirectedComponent} from '../class-directed/class-directed.component';

const routes: Routes = [
  {
    path: 'client', component: HomeComponent,
    canActivate: [AuthGuard]
  }, {
    path: 'routines', component: TablesComponent,
    canActivate: [AuthGuard]
  }, {
    path: 'routine', component: TableComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'exercise', component: ExerciseDetailsComponent,
    canActivate: [AuthGuard]
  }, {
    path: 'classes', component: ClassDirectedComponent,
    canActivate: [AuthGuard]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule {
}
