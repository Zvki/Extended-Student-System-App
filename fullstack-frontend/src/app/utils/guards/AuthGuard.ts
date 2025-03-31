import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../AuthService';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  
  constructor(private router: Router, private authService: AuthService) {}

  async canActivate(): Promise<boolean> {
    const userData = sessionStorage.getItem('user'); 
    const isLoggedIn = await firstValueFrom(this.authService.isLoggedIn$)
    if (userData) {
      return isLoggedIn; 
    } else {
      this.router.navigate(['/login']); 
      return isLoggedIn;
    }
  }
}