import {Component, Input, input, InputSignal} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HotelResponse} from '../../services/book/models/hotel-response';

@Component({
  selector: 'app-hotel-list',
  imports: [FormsModule],
  templateUrl: './hotel-list.component.html',
  styleUrl: './hotel-list.component.scss'
})
export class HotelListComponent {
  @Input() hotels: HotelResponse[] = [];
}
