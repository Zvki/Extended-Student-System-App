import { CommonModule } from '@angular/common';
import { Component, ContentChild, Input, TemplateRef, ViewEncapsulation } from '@angular/core';
import { Observable} from 'rxjs';
import { Subject } from '../../../utils/interfaces/SubjectInterface';
import { Enrollment } from '../../../utils/interfaces/EnrollmentInterfaces';

@Component({
  selector: 'app-enrollment-item',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './enrollment-item.component.html',
  styleUrl: './enrollment-item.component.css'
})
export class EnrollmentItemComponent {
  @Input() subjects$: Observable<(Subject | Enrollment)[]> | undefined;

  @ContentChild(TemplateRef) enrollButtonTemplate: TemplateRef<{subject: Subject}> | undefined;

  expandedEnrollments: boolean[] = [];

  toggleDescription(index: number): void {
    this.expandedEnrollments[index] = !this.expandedEnrollments[index];
  }

}
