import { Component } from '@angular/core';
import { NavbarComponent } from '../../components/navbar/navbar.component';
import { FooterComponent } from '../../components/footer/footer.component';
import { EnrollmentHeroComponent } from '../../components/enrollment-hero/enrollment-hero.component';

@Component({
  selector: 'app-enrollment-page',
  standalone: true,
  imports: [NavbarComponent, FooterComponent, EnrollmentHeroComponent],
  templateUrl: './enrollment-page.component.html',
  styleUrl: './enrollment-page.component.css'
})
export class EnrollmentPageComponent {

}
