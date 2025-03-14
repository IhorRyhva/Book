import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {KeycloakService} from './services/keycloak/keycloak.service';
import {UserProfile} from './services/keycloak/user-profile';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'book-ui';
  profile?: UserProfile;

  constructor(private keycloakService: KeycloakService) {}

  ngOnInit() {
    this.profile = this.keycloakService.profile;
  }

  logout() {
    this.keycloakService.logout();
  }
}
