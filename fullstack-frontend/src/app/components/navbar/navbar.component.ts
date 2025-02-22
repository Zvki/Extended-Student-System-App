import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../utils/AuthService';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

}
