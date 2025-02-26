import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HotelsRoutingModule } from './hotels-routing.module';
import {MenuComponent} from './commponets/menu/menu.component';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {AppRoutingModule} from '../../app.routes';
import {provideHttpClient} from '@angular/common/http';
import {AppComponent} from '../../app.component';


@NgModule({
  declarations: [MenuComponent],
  imports: [
    CommonModule,
    HotelsRoutingModule,
    BrowserModule,
    AppRoutingModule
  ],
  providers: [
    provideHttpClient(),
  ],
  exports: [
    MenuComponent
  ],
  bootstrap: [AppComponent]
})
export class HotelsModule { }
