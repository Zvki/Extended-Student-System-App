import { Component } from '@angular/core';
import { AuthService } from '../../utils/AuthService';
import { AsyncPipe, NgIf } from '@angular/common';
import { UserService } from '../../utils/UserService';
import { RouterLink } from '@angular/router';
import { map } from 'rxjs';
import { DashboardHeaderComponent } from '../dashboard-header/dashboard-header.component';
import { BaseLayoutComponent } from '../base-layout/base-layout.component';
import { Role } from '../../utils/types/UserDataInterface';

@Component({
  selector: 'app-landing-hero',
  standalone: true,
  imports: [NgIf, AsyncPipe, RouterLink, DashboardHeaderComponent, BaseLayoutComponent],
  providers: [AuthService],
  templateUrl: './landing-hero.component.html',
  styleUrl: './landing-hero.component.css'
})
export class LandingHeroComponent {

  isLoggedIn = false;

  name$ = this.userService.user$.pipe(map(user => user?.name))
  role$ = this.userService.user$.pipe(map(user => user?.role))

  isStudent$ = this.role$.pipe(map(role => role === Role.Student))
  isTeacher$ = this.role$.pipe(map(role => role === Role.Teacher))

  constructor(private AuthService: AuthService, private userService: UserService) {}

  ngOnInit(): void {
    if(this.userService.getUser() !== undefined ){
      this.isLoggedIn = true 
    } else {
      this.isLoggedIn = false
    }
  }

}
