import { Component } from '@angular/core';
import { EnrolledSubjectsComponent } from './enrolled-subjects/enrolled-subjects.component';

@Component({
  selector: 'app-enrollment-hero',
  standalone: true,
  imports: [EnrolledSubjectsComponent],
  templateUrl: './enrollment-hero.component.html',
  styleUrl: './enrollment-hero.component.css'
})
export class EnrollmentHeroComponent {

}

