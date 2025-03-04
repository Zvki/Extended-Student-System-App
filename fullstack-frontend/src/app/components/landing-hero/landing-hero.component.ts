import { Component } from '@angular/core';
import { AuthService } from '../../utils/AuthService';
import { AsyncPipe, NgIf } from '@angular/common';
import { UserService } from '../../utils/UserService';
import { RouterLink } from '@angular/router';
import { map } from 'rxjs';
import { DashboardHeaderComponent } from '../dashboard-header/dashboard-header.component';

@Component({
  selector: 'app-landing-hero',
  standalone: true,
  imports: [NgIf, AsyncPipe, RouterLink, DashboardHeaderComponent],
  templateUrl: './landing-hero.component.html',
  styleUrl: './landing-hero.component.css'
})
export class LandingHeroComponent {

  isLoggedIn$ = this.AuthService.isLoggedIn$;
  name$ = this.userService.user$.pipe(map(user => user?.name));

  constructor(private AuthService: AuthService, private userService: UserService) {}

}
