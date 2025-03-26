import { Component } from '@angular/core';
import { FooterComponent } from '../../components/footer/footer.component';
import { NavbarComponent } from '../../components/navbar/navbar.component';
import { ChooseSubjectComponent } from '../../components/choose-subject/choose-subject.component';

@Component({
  selector: 'app-assign-grades-page',
  standalone: true,
  imports: [FooterComponent, NavbarComponent, ChooseSubjectComponent],
  templateUrl: './assign-grades-page.component.html',
  styleUrl: './assign-grades-page.component.css'
})
export class AssignGradesPageComponent {

}
