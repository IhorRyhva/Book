import {AppComponent} from './app.component';
import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {provideHttpClient} from '@angular/common/http';

import {AppRoutingModule} from './app.routes';
import {MainComponent} from './modules/hotels/pages/main/main.component';
import {HotelsModule} from './modules/hotels/hotels.module';
import {MenuComponent} from './modules/hotels/commponets/menu/menu.component';
@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    MenuComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HotelsModule
  ],
  providers: [
    provideHttpClient(),
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
