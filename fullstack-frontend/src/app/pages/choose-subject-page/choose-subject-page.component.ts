import { Component } from '@angular/core';
import { ChooseSubjectComponent } from '../../components/choose-subject/choose-subject.component';
import { FooterComponent } from '../../components/footer/footer.component';
import { NavbarComponent } from '../../components/navbar/navbar.component';

@Component({
  selector: 'app-choose-subject-page',
  standalone: true,
  imports: [ChooseSubjectComponent, FooterComponent, NavbarComponent],
  templateUrl: './choose-subject-page.component.html',
  styleUrl: './choose-subject-page.component.css'
})
export class ChooseSubjectPageComponent {

}
