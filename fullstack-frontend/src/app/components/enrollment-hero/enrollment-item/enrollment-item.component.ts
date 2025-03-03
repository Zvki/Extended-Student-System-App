import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-enrollment-item',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './enrollment-item.component.html',
  styleUrl: './enrollment-item.component.css'
})
export class EnrollmentItemComponent {
  @Input() subjects$: Observable<any[]> | undefined;  
  expandedEnrollments: boolean[] = [];

  toggleDescription(index: number): void {
    this.expandedEnrollments[index] = !this.expandedEnrollments[index];
  }

}
