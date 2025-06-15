import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {AppComponent} from './app/app.component';
import {MainComponent} from './app/pages/main/main.component';
import {HotelListComponent} from './app/components/hotel-list/hotel-list.component';



@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    HotelListComponent,
  ],
  imports:[
    BrowserModule
  ],
  providers:[],
  bootstrap:[
    AppComponent
  ]
})
export class AppModule { }
