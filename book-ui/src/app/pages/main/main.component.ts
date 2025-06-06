import {Component, OnInit} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {KeycloakService} from '../../utils/keycloak/keycloak.service';
import {HotelControllerService} from '../../services/hotel/services/hotel-controller.service';
import {HotelResponse} from '../../services/hotel/models/hotel-response';
import {HotelsComponent} from '../../hotels/hotels.component';


@Component({
  selector: 'app-main',
  imports: [
    FormsModule,
    HotelsComponent
  ],
  templateUrl: './main.component.html',
  styleUrl: './main.component.scss',
})
export class MainComponent implements OnInit{
  hotels: Array<HotelResponse> = [];

  constructor(
    private keyCloak: KeycloakService,
    private hotelService: HotelControllerService
  ){}

  ngOnInit(): void {
    this.getAllHotels();
  }

  login() {
    this.keyCloak.login();
  }

  private getAllHotels(){
    this.hotelService.getAllHotels$Response()
      .subscribe({
        next: (res) => {
          console.log("Geting data" +res.body)
          this.hotels = res.body;
        }
      })
  }


}
