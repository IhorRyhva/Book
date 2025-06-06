import {Component, Input, input, InputSignal} from '@angular/core';
import {HotelResponse} from '../services/hotel/models/hotel-response';

@Component({
  selector: 'app-hotels',
  imports: [],
  templateUrl: './hotels.component.html',
  styleUrl: './hotels.component.scss'
})
export class HotelsComponent {
  @Input() hotels: HotelResponse[] = [];
}
