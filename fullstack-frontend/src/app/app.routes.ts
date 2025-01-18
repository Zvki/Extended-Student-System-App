import { Routes } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';
import { LandingPageComponent } from './landing-page/landing-page.component';

export const routes: Routes = [
{ path: '', component: LandingPageComponent },
{ path: 'login', component: LoginPageComponent }
];
