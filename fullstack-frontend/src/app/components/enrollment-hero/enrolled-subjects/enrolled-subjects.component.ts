import { Component } from '@angular/core';
import { BehaviorSubject, map, Observable } from 'rxjs';
import { Enrollment } from '../../../utils/interfaces/EnrollmentInterfaces';
import { HttpClient } from '@angular/common/http';
import { UserService } from '../../../utils/UserService';
import { AsyncPipe, CommonModule } from '@angular/common';
import { SubjectPlaceholderComponent } from '../../subject-placeholder/subject-placeholder.component';
import { EnrollmentItemComponent } from '../enrollment-item/enrollment-item.component';
import { DashboardHeaderComponent } from '../../dashboard-header/dashboard-header.component';
import { BaseLayoutComponent } from '../../base-layout/base-layout.component';

@Component({
  selector: 'app-enrolled-subjects',
  standalone: true,
  imports: [AsyncPipe, CommonModule, SubjectPlaceholderComponent, EnrollmentItemComponent, DashboardHeaderComponent, BaseLayoutComponent],
  templateUrl: './enrolled-subjects.component.html',
  styleUrl: './enrolled-subjects.component.css'
})
export class EnrolledSubjectsComponent {

  subjects$: Observable<Enrollment[]> | undefined;
  id$ = this.userService.user$.pipe(map(user => user?.id));
  isLoading: boolean = true;
  expandedEnrollments: { [key: number]: boolean } = {};

  constructor(private http: HttpClient, private userService: UserService) {}



  ngOnInit(): void {

    setTimeout(() => {
    this.id$.subscribe(id => {
      this.http.get<{ success: boolean; message: string; data: any }>(`http://localhost:8080/getenrollments/${id}`)
        .subscribe({
          next: (response) => {
            console.log('Enrollments received', response)
            this.subjects$ = new BehaviorSubject<Enrollment[]>(response.data).asObservable();
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