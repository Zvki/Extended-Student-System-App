import { Component } from '@angular/core';
import { UserService } from '../../utils/UserService';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { AsyncPipe, CommonModule } from '@angular/common';
import { BehaviorSubject, Observable } from 'rxjs';
import { Subject } from '../../utils/interfaces/SubjectInterface';
import { RouterLink } from '@angular/router';
import { BaseLayoutComponent } from '../base-layout/base-layout.component';
import { DashboardHeaderComponent } from '../dashboard-header/dashboard-header.component';

@Component({
  selector: 'app-choose-subject',
  standalone: true,
  imports: [AsyncPipe, RouterLink, CommonModule, BaseLayoutComponent, DashboardHeaderComponent],
  templateUrl: './choose-subject.component.html',
  styleUrl: './choose-subject.component.css'
})
export class ChooseSubjectComponent {

  subjects$: Observable<Subject[]> | undefined
  id$ = this.userService.user$.pipe(map(user => user?.id))

  constructor(private http: HttpClient, private userService: UserService) {}

  ngOnInit(): void {
    this.id$.subscribe(id => {
      this.http.get<{ success: boolean; message: string; data: any }>(`http://localhost:8080/usersteachsubjects/${id}`)
        .subscribe(
          {next: (response) => {
              this.subjects$ = new BehaviorSubject<Subject[]>(response.data).asObservable();
              console.log(`Subjects: `, this.subjects$)
            },
          error: (error) => {
              console.log(`Error occured`, error)
          } },
        )
    })  
  }

}
