import { Component } from '@angular/core';
import { NavbarComponent } from '../../components/navbar/navbar.component';
import { FooterComponent } from '../../components/footer/footer.component';
import { RegisterComponent } from '../../components/register/register.component';

@Component({
  selector: 'app-register-page',
  standalone: true,
  imports: [NavbarComponent, FooterComponent, RegisterComponent],
  templateUrl: './register-page.component.html',
  styleUrl: './register-page.component.css'
})
export class RegisterPageComponent {

}
