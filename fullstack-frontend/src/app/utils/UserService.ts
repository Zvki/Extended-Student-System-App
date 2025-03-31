import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { BehaviorSubject } from 'rxjs';
import { UserData } from './types/UserDataInterface';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private userSubject = new BehaviorSubject<UserData | undefined>(undefined);
  user$ = this.userSubject.asObservable();

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {
    if (isPlatformBrowser(this.platformId)) {
      this.loadUserFromStorage();
    }
  }

  setUser(userData: UserData): void {
    console.log('Before save: ', userData)
    if (isPlatformBrowser(this.platformId)) {
      sessionStorage.setItem('user', JSON.stringify(userData));
    }
    this.userSubject.next(userData);
    console.log('After save: ', userData)
  }

  getUser(): UserData | undefined {
    if (this.userSubject.value !== null) {
      return this.userSubject.value;
    }
    return undefined;
  }

  getUserId(): number | null {
    return this.userSubject.value?.id ?? null;
  }

  clearUser(): void {
    if (isPlatformBrowser(this.platformId)) {
      sessionStorage.removeItem('user');
    }
    this.userSubject.next(undefined);
  }

  private loadUserFromStorage(): void {
    if (isPlatformBrowser(this.platformId)) {
      const storedUser = sessionStorage.getItem('user');
      if (storedUser) {
        this.userSubject.next(JSON.parse(storedUser));
      }
    }
  }
}