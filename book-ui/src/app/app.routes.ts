import { Routes } from '@angular/router';
import {MainComponent} from './pages/main/main.component';
import {LoginedComponent} from './pages/log-in/log-in.component';


export const routes: Routes = [
  {
    path: '',
    component: MainComponent
  },
  {
    path: 'logined',
    component: LoginedComponent
  }
];
