import { Component } from '@angular/core';
import {KeycloakService} from '../../utils/keycloak/keycloak.service';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-log-in',
  imports: [FormsModule],
  templateUrl: './log-in.component.html',
  styleUrl: './log-in.component.scss'
})
export class LoginedComponent {
  constructor(
    private keyCloak: KeycloakService
  ) {
  }
  logout() {
    this.keyCloak.logout();
  }

  userProfile(){
    this.keyCloak.accountManagement();
  }
}
