import { Component } from '@angular/core';
import { BehaviorSubject, map } from 'rxjs';
import { Enrollment } from '../../../utils/interfaces/EnrollmentInterfaces';
import { HttpClient } from '@angular/common/http';
import { UserService } from '../../../utils/UserService';
import { AsyncPipe, CommonModule } from '@angular/common';
import { Subject } from '../../../utils/interfaces/SubjectInterface';
@Component({
  selector: 'app-available-subjects',
  standalone: true,
  imports: [AsyncPipe, CommonModule],
  templateUrl: './available-subjects.component.html',
  styleUrl: './available-subjects.component.css'
})
export class AvailableSubjectsComponent {

  subjects$ = new BehaviorSubject<Subject[]>([]);
  id$ = this.userService.user$.pipe(map(user => user?.id));
  isLoading: boolean = true;
  expandedEnrollments: { [key: number]: boolean } = {};
  placeholderItems = new Array(5);
  
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
              this.subjects$.next(response.data)
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
