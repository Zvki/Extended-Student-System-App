import { Routes } from '@angular/router';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { LandingPageComponent } from './pages/landing-page/landing-page.component';
import { ProfilePageComponent } from './pages/profile-page/profile-page.component';
import { LoadingPageComponent } from './pages/loading-page/loading-page.component';

export const routes: Routes = [
{ path: '', component: LandingPageComponent },
{ path: 'login', component: LoginPageComponent },
{ path: 'profile', component: ProfilePageComponent},
{ path: 'loading', component: LoadingPageComponent}

];
