import { Injectable } from '@angular/core';
import Keycloak from 'keycloak-js';
import { UserProfile } from './user-profile';

@Injectable({
  providedIn: 'root',
})
export class KeycloakService {
  private _keycloak?: Keycloak;
  private _profile?: UserProfile;

  constructor() {}

  get keycloak(): Keycloak {
    if (!this._keycloak) {
      this._keycloak = new Keycloak({
        url: 'http://localhost:9090',
        realm: 'book-hotel',
        clientId: 'krasavchik',
      });
    }
    return this._keycloak;
  }

  get profile(): UserProfile | undefined {
    return this._profile;
  }

  async init(): Promise<boolean> {
    console.log('Authenticating the user...');
    try {
      const authenticated = await this.keycloak.init({ onLoad: 'login-required' });

      if (authenticated) {
        console.log('User authenticated...');
        this._profile = (await this.keycloak.loadUserProfile()) as UserProfile;
      }

      return authenticated;
    } catch (error) {
      console.error('Keycloak initialization failed:', error);
      return false;
    }
  }

  getToken(): string | undefined {
    return this.keycloak.token;
  }

  async login(): Promise<void> {
    await this.keycloak.login();
  }

  async logout(): Promise<void> {
    await this.keycloak.logout({ redirectUri: window.location.origin });
  }
}
