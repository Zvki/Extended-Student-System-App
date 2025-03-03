import { Component } from '@angular/core';
import { BehaviorSubject, map, Observable } from 'rxjs';
import { Enrollment } from '../../../utils/interfaces/EnrollmentInterfaces';
import { HttpClient } from '@angular/common/http';
import { UserService } from '../../../utils/UserService';
import { AsyncPipe, CommonModule } from '@angular/common';
import { Subject } from '../../../utils/interfaces/SubjectInterface';
import { SubjectPlaceholderComponent } from '../../subject-placeholder/subject-placeholder.component';
@Component({
  selector: 'app-available-subjects',
  standalone: true,
  imports: [AsyncPipe, CommonModule, SubjectPlaceholderComponent],
  templateUrl: './available-subjects.component.html',
  styleUrl: './available-subjects.component.css'
})
export class AvailableSubjectsComponent {

  subjects$: Observable<Subject[]> | undefined;
  id$ = this.userService.user$.pipe(map(user => user?.id));
  isLoading: boolean = true;
  expandedEnrollments: { [key: number]: boolean } = {};
  
  constructor(private http: HttpClient, private userService: UserService) {}
  
  enroll(subjectId: number): void {
      this.id$.subscribe(id => {
        this.http.post(`http://localhost:8080/${id}/enroll/${subjectId}`, {}).subscribe({
          next: (response) => {
            console.log('Enrollment successful', response);
            window.location.reload();
          },
          error: (error) => {
            console.log('Error occurred during enrollment', error);
          }
        })
      })
  }
  
  
  ngOnInit(): void {

    this.getEnrollments();

  }
  
    toggleDescription(index: number): void {
      this.expandedEnrollments[index] = !this.expandedEnrollments[index];
  }

  getEnrollments(): void {

    setTimeout(() => {
      this.id$.subscribe(id => {
        this.http.get<{ success: boolean; message: string; data: any }>(`http://localhost:8080/usersavailablesubjects/${id}`)
          .subscribe({
            next: (response) => {
              console.log('Subject received', response)
              this.subjects$ = new BehaviorSubject<Subject[]>(response.data).asObservable();
              this.isLoading = false;
            },
            error: (error) => {
              console.log('Error occured', error)
            }
          })
      });
    }, 1500);

  }

}
