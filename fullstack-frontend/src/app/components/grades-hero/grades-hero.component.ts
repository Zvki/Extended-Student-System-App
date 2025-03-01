import { Component } from '@angular/core';
import { Enrollment } from '../../utils/interfaces/EnrollmentInterfaces';
import { HttpClient} from '@angular/common/http';
import { UserService } from '../../utils/UserService';
import { BehaviorSubject, map } from 'rxjs';
import { AsyncPipe, CommonModule } from '@angular/common';

@Component({
  selector: 'app-grades-hero',
  standalone: true,
  imports: [AsyncPipe, CommonModule],
  templateUrl: './grades-hero.component.html',
  styleUrl: './grades-hero.component.css'
})
export class GradesHeroComponent {

  grades$ = new BehaviorSubject<Enrollment[]>([]);
  id$ = this.userService.user$.pipe(map(user => user?.id));
  isLoading: boolean = true;
  placeholderItems = new Array(5);

  constructor(private http: HttpClient, private userService: UserService) {}



  ngOnInit(): void {

    setTimeout(() => {
    this.id$.subscribe(id => {
      this.http.get<{ success: boolean; message: string; data: any }>(`http://localhost:8080/getenrollments/${id}`)
        .subscribe({
          next: (response) => {
            console.log('Grades received', response)
            this.grades$.next(response.data)
            this.isLoading = false;
          },
          error: (error) => {
            console.log('Error occured', error)
          }
        })
    });}, 1500);
  }

}
