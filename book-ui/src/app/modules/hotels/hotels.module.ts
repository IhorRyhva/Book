import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HotelsRoutingModule } from './hotels-routing.module';
import {MenuComponent} from './commponets/menu/menu.component';
import {provideHttpClient} from '@angular/common/http';



@NgModule({
  declarations: [MenuComponent],
  imports: [
    CommonModule,
    HotelsRoutingModule,
  ],
  providers: [
    provideHttpClient(),
  ],
  exports: [
    MenuComponent
  ],
})
export class HotelsModule { }
