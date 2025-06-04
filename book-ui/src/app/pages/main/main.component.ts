import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {KeycloakService} from '../../utils/keycloak/keycloak.service';

@Component({
  selector: 'app-main',
  imports: [FormsModule],
  templateUrl: './main.component.html',
  styleUrl: './main.component.scss'
})
export class MainComponent {
  constructor(
    private keyCloak: KeycloakService
  ){}
  login() {
    this.keyCloak.login();
  }

}
