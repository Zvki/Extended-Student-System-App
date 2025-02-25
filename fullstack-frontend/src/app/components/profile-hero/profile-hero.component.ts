import { Component } from '@angular/core';
import { UserService } from '../../utils/UserService';
import { map } from 'rxjs';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-profile-hero',
  standalone: true,
  imports: [AsyncPipe],
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
