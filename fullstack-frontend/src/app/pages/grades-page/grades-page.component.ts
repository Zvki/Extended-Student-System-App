import { Component } from '@angular/core';
import { NavbarComponent } from '../../components/navbar/navbar.component';
import { FooterComponent } from '../../components/footer/footer.component';
import { GradesHeroComponent } from '../../components/grades-hero/grades-hero.component';

@Component({
  selector: 'app-grades-page',
  standalone: true,
  imports: [NavbarComponent, FooterComponent, GradesHeroComponent],
  templateUrl: './grades-page.component.html',
  styleUrl: './grades-page.component.css'
})
export class GradesPageComponent {

}
