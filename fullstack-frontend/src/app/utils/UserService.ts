import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private userSubject = new BehaviorSubject<any | null>(null);
  user$ = this.userSubject.asObservable();

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {
    if (isPlatformBrowser(this.platformId)) {
      this.loadUserFromStorage();
    }
  }

  setUser(userData: any): void {
    console.log('Before save: ', userData)
    if (isPlatformBrowser(this.platformId)) {
      try {
        if (window.sessionStorage) {
          sessionStorage.setItem('user', JSON.stringify(userData));
        } else {
          console.error('sessionStorage is not available');
        }
      } catch (error) {
        console.error('Error saving user to sessionStorage:', error);
      }
    }
    this.userSubject.next(userData);
    console.log('After save: ', userData)
  }

  getUser(): any {
    return this.userSubject.value;
  }

  getUserId(): number | null {
    return this.userSubject.value?.id ?? null;
  }

  clearUser(): void {
    if (isPlatformBrowser(this.platformId)) {
      try {
        if (window.sessionStorage) {
          sessionStorage.removeItem('user');
        } else {
          console.error('sessionStorage is not available');
        }
      } catch (error) {
        console.error('Error clearing user from sessionStorage:', error);
      }
    }
    this.userSubject.next(null);
  }

  private loadUserFromStorage(): void {
    if (isPlatformBrowser(this.platformId)) {
      try {
        if (window.sessionStorage) {
          const storedUser = sessionStorage.getItem('user');
          if (storedUser) {
            this.userSubject.next(JSON.parse(storedUser));
          }
        } else {
          console.error('sessionStorage is not available');
        }
      } catch (error) {
        console.error('Error loading user from sessionStorage:', error);
      }
    }
  }
}