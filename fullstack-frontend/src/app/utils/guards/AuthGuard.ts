import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  
  constructor(private router: Router) {}

  canActivate(): boolean {
    const userData = sessionStorage.getItem('user'); 
    if (userData) {
      return true; 
    } else {
      this.router.navigate(['/login']); 
      return false;
    }
  }
}