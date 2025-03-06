import { Component } from '@angular/core';
import { EditProfileComponent } from '../../components/edit-profile/edit-profile.component';
import { NavbarComponent } from '../../components/navbar/navbar.component';
import { FooterComponent } from '../../components/footer/footer.component';

@Component({
  selector: 'app-edit-profile-page',
  standalone: true,
  imports: [EditProfileComponent, NavbarComponent, FooterComponent ],
  templateUrl: './edit-profile-page.component.html',
  styleUrl: './edit-profile-page.component.css'
})
export class EditProfilePageComponent {

}
