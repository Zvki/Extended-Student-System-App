import { Component } from '@angular/core';
import { AuthService } from '../../utils/AuthService';
import { AsyncPipe, NgIf } from '@angular/common';

@Component({
  selector: 'app-landing-hero',
  standalone: true,
  imports: [NgIf, AsyncPipe],
  templateUrl: './landing-hero.component.html',
  styleUrl: './landing-hero.component.css'
})
export class LandingHeroComponent {

  isLoggedIn$ = this.AuthService.isLoggedIn$;

  constructor(private AuthService: AuthService) {}

}
