import { Component } from '@angular/core';
import { UserService } from '../../utils/UserService';
import { map } from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { DashboardHeaderComponent } from '../dashboard-header/dashboard-header.component';
import { BaseLayoutComponent } from '../base-layout/base-layout.component';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-profile-hero',
  standalone: true,
  imports: [AsyncPipe, DashboardHeaderComponent, BaseLayoutComponent, RouterLink],
  templateUrl: './profile-hero.component.html',
  styleUrl: './profile-hero.component.css'
})
export class ProfileHeroComponent {

  constructor(private userService: UserService) {}

  name$ = this.userService.user$.pipe(map(user => user?.name));
  surname$ = this.userService.user$.pipe(map(user => user?.surname));
  email$ = this.userService.user$.pipe(map(user => user?.email));
  role$ = this.userService.user$.pipe(map(user => user?.role));

}
