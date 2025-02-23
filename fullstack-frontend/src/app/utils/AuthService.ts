import { isPlatformBrowser } from '@angular/common';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  private isLoggedIn = new BehaviorSubject<boolean>(false);
  isLoggedIn$ = this.isLoggedIn.asObservable();

  constructor(@Inject(PLATFORM_ID) private platformId: Object, private router: Router) {
    this.checkAuthStatus();
    this.listenForRouteChanges();
  }

  private checkAuthStatus(): void {
    if (isPlatformBrowser(this.platformId)) {
      const isLoggedIn = this.getCookie('authToken') !== '';
      this.isLoggedIn.next(isLoggedIn);
    }
  }

  private listenForRouteChanges(): void {
    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe(() => {
        this.checkAuthStatus();
      });
  }

  logout(): void {
    this.deleteCookie('authToken');
    this.isLoggedIn.next(false);
  }

  private getCookie(name: string): string {
    const cookieName = `${name}=`;
    const cookies = document.cookie.split(';');
    for (let i = 0; i < cookies.length; i++) {
      let cookie = cookies[i].trim();
      if (cookie.startsWith(cookieName)) {
        return cookie.substring(cookieName.length, cookie.length);
      }
    }
    return '';
  }

  private deleteCookie(name: string): void {
    document.cookie = `${name}=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;`;
  }
  
}
