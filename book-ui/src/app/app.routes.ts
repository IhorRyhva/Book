import {RouterModule, Routes} from '@angular/router';
import {HotelsComponent} from './pages/hotels/hotels.component';
import {NgModule} from '@angular/core';

export const routes: Routes = [
  {
    path: 'hotels/hotel',
    component: HotelsComponent
  }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
