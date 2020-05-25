import {NgModule} from '@angular/core';

import {ClientRoutingModule} from './client-routing.module';
import {HomeComponent} from './home/home.component';
import {EvenOrOdd} from '../../utilities/even-or-odd';
import {TablesComponent} from './tables/tables.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {TableComponent} from './table/table.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';
import {AlertComponent} from '../alert/alert.component';
import {ExerciseDetailsComponent} from './exercise-details/exercise-details.component';
import {ClassDirectedComponent} from '../class-directed/class-directed.component';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
  declarations: [
    HomeComponent,
    TablesComponent,
    TableComponent,
    EvenOrOdd,
    AlertComponent,
    ExerciseDetailsComponent,
    ClassDirectedComponent

  ],
  imports: [
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    NgbModule,
    FontAwesomeModule,
    ClientRoutingModule,

  ],
  exports: [
    AlertComponent,
    ClassDirectedComponent
  ]
})
export class ClientModule {
}
