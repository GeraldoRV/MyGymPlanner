import {NgModule} from '@angular/core';
import {AlertComponent} from '../alert/alert.component';
import {ClassDirectedComponent} from '../class-directed/class-directed.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';

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
      NgbModule
    ],
    exports: [AlertComponent,
      ClassDirectedComponent]
  }
)
export class CommonComponentsModule {
}
