import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../utils/AuthService';
import { NgIf, AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink, NgIf, AsyncPipe],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  isLoggedIn$ = this.authService.isLoggedIn$;

  constructor(private authService: AuthService) {}

  logout(): void {
    this.authService.logout();
  }

}
