import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {AppComponent} from './app/app.component';
import {MainComponent} from './app/pages/main/main.component';
import {LoginedComponent} from './app/pages/log-in/log-in.component';



@NgModule({
  declarations:[
    AppComponent,
    MainComponent,
    LoginedComponent
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
