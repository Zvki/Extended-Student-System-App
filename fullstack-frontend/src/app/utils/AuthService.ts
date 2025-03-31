import { isPlatformBrowser } from '@angular/common';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { catchError, filter, map } from 'rxjs/operators';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { UserService } from './UserService';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root',
})
export class AuthService {

  private isLoggedIn = new BehaviorSubject<boolean>(false);
  isLoggedIn$ = this.isLoggedIn.asObservable();

  constructor(@Inject(PLATFORM_ID) private platformId: Object, private router: Router, private userService: UserService, private http: HttpClient ) {
    this.checkAuthStatus();
    this.listenForRouteChanges();
  }

  login(userData: any): void {
    this.userService.setUser(userData);
  }

  public checkAuthStatus(): Observable<boolean> {
    if (isPlatformBrowser(this.platformId)) {
      return this.http.get<{ authenticated: boolean }>(`${environment.apiUrl}checkauth`, { withCredentials: true })
        .pipe(
          map(response => {
            this.isLoggedIn.next(response.authenticated);
            return response.authenticated;
          }),
          catchError(() => {
            this.isLoggedIn.next(false);
            return of(false);
          })
        );
    }
    return of(false);
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
    this.userService.clearUser();
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
    this.http.delete(`${environment.apiUrl}logout`, { withCredentials: true });
  }
  
}
