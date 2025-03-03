import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-subject-placeholder',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './subject-placeholder.component.html',
  styleUrl: './subject-placeholder.component.css'
})
export class SubjectPlaceholderComponent {

  placeholderItems = new Array(5);

}
