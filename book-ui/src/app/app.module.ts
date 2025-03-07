import {AppComponent} from './app.component';
import {APP_INITIALIZER, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {provideHttpClient} from '@angular/common/http';

import {AppRoutingModule} from './app.routes';
import {MainComponent} from './modules/hotels/pages/main/main.component';
import {HotelsModule} from './modules/hotels/hotels.module';
import {MenuComponent} from './modules/hotels/commponets/menu/menu.component';
import {KeycloakService} from './services/keycloak/keycloak.service';
import {LoginComponent} from './services/auth-user/login.component';
export function kcFactory(kcService: KeycloakService){
  return () => kcService.init();
}
@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    MenuComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HotelsModule
  ],
  providers: [
    provideHttpClient(),
    {
      provide: APP_INITIALIZER,
      deps: [KeycloakService],
      useFactory: kcFactory,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
