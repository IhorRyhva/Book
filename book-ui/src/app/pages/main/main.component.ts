import {Component, OnInit} from '@angular/core';
import {HotelResponse} from '../../services/book/models/hotel-response';
import {HotelControllerService} from '../../services/hotel/services/hotel-controller.service';
import {HotelListComponent} from '../../components/hotel-list/hotel-list.component';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-main',
  imports: [
    FormsModule,
    HotelListComponent
  ],
  templateUrl: './main.component.html',
  styleUrl: './main.component.scss'
})
export class MainComponent implements OnInit{

  hotels: Array<HotelResponse> = [];

  constructor(
    private hotelService: HotelControllerService
  ) {
  }

  ngOnInit(): void {
    this.getAllHotels();
  }

  private getAllHotels(){
    this.hotelService.getAllHotels$Response()
      .subscribe({
        next: (res) => {
          this.hotels = res.body;
        }
      });
    console.log(this.hotels);
  }

}
