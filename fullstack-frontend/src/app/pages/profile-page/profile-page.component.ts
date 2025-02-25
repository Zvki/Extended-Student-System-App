import { Component } from '@angular/core';
import { NavbarComponent } from '../../components/navbar/navbar.component';
import { FooterComponent } from '../../components/footer/footer.component';
import { ProfileHeroComponent } from '../../components/profile-hero/profile-hero.component';

@Component({
  selector: 'app-profile-page',
  standalone: true,
  imports: [NavbarComponent, FooterComponent, ProfileHeroComponent],
  templateUrl: './profile-page.component.html',
  styleUrl: './profile-page.component.css'
})
export class ProfilePageComponent {

}
