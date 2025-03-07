import {RouterModule, Routes} from '@angular/router';

import {NgModule} from '@angular/core';
import {MainComponent} from './modules/hotels/pages/main/main.component';
import {LoginComponent} from './services/auth-user/login.component';

export const routes: Routes = [
  {
    path: 'hotels/hotel',
    //loadChildren: () => import('./modules/hotels/hotels.module').then(m => m.HotelsModule)
    component: MainComponent
  },
  {
    path: 'login',
    component: LoginComponent
  }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
