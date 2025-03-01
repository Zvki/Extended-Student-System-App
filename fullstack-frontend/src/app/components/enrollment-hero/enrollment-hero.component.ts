import { Component } from '@angular/core';
import { EnrolledSubjectsComponent } from './enrolled-subjects/enrolled-subjects.component';
import { AvailableSubjectsComponent } from './available-subjects/available-subjects.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-enrollment-hero',
  standalone: true,
  imports: [EnrolledSubjectsComponent, AvailableSubjectsComponent, CommonModule],
  templateUrl: './enrollment-hero.component.html',
  styleUrl: './enrollment-hero.component.css'
})
export class EnrollmentHeroComponent {

  currentSlide: 'enrolled' | 'available' = 'enrolled';

  showEnrolled() {
    this.currentSlide = 'enrolled';
  }

  showAvailable() {
    this.currentSlide = 'available';
  }

}

