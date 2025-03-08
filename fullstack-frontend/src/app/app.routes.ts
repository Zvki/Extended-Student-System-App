import { Routes } from '@angular/router';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { LandingPageComponent } from './pages/landing-page/landing-page.component';
import { ProfilePageComponent } from './pages/profile-page/profile-page.component';
import { LoadingPageComponent } from './pages/loading-page/loading-page.component';
import { RegisterPageComponent } from './pages/register-page/register-page.component';
import { GradesPageComponent } from './pages/grades-page/grades-page.component';
import { EnrollmentPageComponent } from './pages/enrollment-page/enrollment-page.component';
import { ChangePasswordPageComponent } from './pages/change-password-page/change-password-page.component';
import { EditProfilePageComponent } from './pages/edit-profile-page/edit-profile-page.component';
import { AuthGuard } from './utils/guards/AuthGuard';

export const routes: Routes = [
{ path: '', component: LandingPageComponent },
{ path: 'login', component: LoginPageComponent },
{ path: 'loading', component: LoadingPageComponent},
{ path: 'register', component: RegisterPageComponent},
{ path: 'profile', component: ProfilePageComponent, canActivate: [AuthGuard] },
{ path: 'grades', component: GradesPageComponent, canActivate: [AuthGuard] },
{ path: 'enrollments', component: EnrollmentPageComponent, canActivate: [AuthGuard] },
{ path: 'changepasswd', component: ChangePasswordPageComponent, canActivate: [AuthGuard] },
{ path: 'editprofile', component: EditProfilePageComponent, canActivate: [AuthGuard] }
];
