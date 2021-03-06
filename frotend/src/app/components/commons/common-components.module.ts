import {LOCALE_ID, NgModule} from '@angular/core';
import {AlertComponent} from './alert/alert.component';
import {ClassDirectedComponent} from './class-directed/class-directed.component';
import {NgbAlertModule, NgbTabsetModule} from '@ng-bootstrap/ng-bootstrap';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {ClassDirectedService} from '../../service/class-directed.service';
import {AlertService} from '../../service/alert.service';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';

import localeEs from '@angular/common/locales/es';
import {registerLocaleData} from '@angular/common';

registerLocaleData(localeEs, 'es');

@NgModule(
  {
    declarations:
      [
        AlertComponent,
        ClassDirectedComponent
      ],
    imports: [
      CommonModule,
      FormsModule,
      ReactiveFormsModule,
      BrowserModule,
      HttpClientModule,
      FontAwesomeModule,
      NgbAlertModule,
      NgbTabsetModule,
      MatButtonModule,
      MatIconModule
    ],
    providers: [ClassDirectedService, AlertService, {provide: LOCALE_ID, useValue: 'es'}],
    exports: [AlertComponent,
      ClassDirectedComponent]
  }
)
export class CommonComponentsModule {
}
