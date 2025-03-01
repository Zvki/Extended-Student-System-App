import { Component } from '@angular/core';
import { BehaviorSubject, map } from 'rxjs';
import { Enrollment } from '../../../utils/interfaces/EnrollmentInterfaces';
import { HttpClient } from '@angular/common/http';
import { UserService } from '../../../utils/UserService';
import { AsyncPipe, CommonModule } from '@angular/common';

@Component({
  selector: 'app-enrolled-subjects',
  standalone: true,
  imports: [AsyncPipe, CommonModule],
  templateUrl: './enrolled-subjects.component.html',
  styleUrl: './enrolled-subjects.component.css'
})
export class EnrolledSubjectsComponent {

  enrollments$ = new BehaviorSubject<Enrollment[]>([]);
  id$ = this.userService.user$.pipe(map(user => user?.id));
  isLoading: boolean = true;
  expandedEnrollments: { [key: number]: boolean } = {};
  placeholderItems = new Array(5);

  constructor(private http: HttpClient, private userService: UserService) {}



  ngOnInit(): void {

    setTimeout(() => {
    this.id$.subscribe(id => {
      this.http.get<{ success: boolean; message: string; data: any }>(`http://localhost:8080/getenrollments/${id}`)
        .subscribe({
          next: (response) => {
            console.log('Enrollments received', response)
            this.enrollments$.next(response.data)
            this.isLoading = false;
          },
          error: (error) => {
            console.log('Error occured', error)
          }
        })
    });}, 1500);
  }

  toggleDescription(index: number): void {
    this.expandedEnrollments[index] = !this.expandedEnrollments[index];
  }
}