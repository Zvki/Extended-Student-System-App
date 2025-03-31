import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../utils/AuthService';
import { NgIf, AsyncPipe } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { UserService } from '../../utils/UserService';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink, NgIf, AsyncPipe, HttpClientModule],
  providers: [AuthService],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  isLoggedIn = false;

  constructor(private authService: AuthService, private router: Router, private userService: UserService) {}

  ngOnInit(): void {
    if(this.userService.getUser() !== undefined ){
      this.isLoggedIn = true 
    } else {
      this.isLoggedIn = false
    }
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate([""]).then(() => {
      window.location.reload();
    });
  }

}
