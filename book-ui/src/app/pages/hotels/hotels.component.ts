import { Component } from '@angular/core';
import {HotelRequest} from '../../services/models/hotel-request';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-hotels',
  imports: [FormsModule],
  templateUrl: './hotels.component.html',
  styleUrl: './hotels.component.scss'
})
export class HotelsComponent {
  hotelRequest: HotelRequest = {name: ''}
}
