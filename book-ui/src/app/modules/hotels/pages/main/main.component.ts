import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HotelRequest} from '../../../../services/models/hotel-request';
import {RouterModule} from '@angular/router';
import {MenuComponent} from '../../commponets/menu/menu.component';

@Component({
  selector: 'app-main',
  imports: [FormsModule,
  RouterModule, MenuComponent],
  templateUrl: './main.component.html',
  styleUrl: './main.component.scss'
})
export class MainComponent {
  hotelRequest: HotelRequest = {name: ''}
}
