import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {AppComponent} from './app/app.component';
import {MainComponent} from './app/pages/main/main.component';


@NgModule({
  declarations:[
    AppComponent,
    MainComponent
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
