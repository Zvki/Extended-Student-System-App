import { Component } from '@angular/core';
import { UserService } from '../../utils/UserService';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { AsyncPipe } from '@angular/common';
import { Observable } from 'rxjs';
import { Subject } from '../../utils/interfaces/SubjectInterface';

@Component({
  selector: 'app-choose-subject',
  standalone: true,
  imports: [AsyncPipe],
  templateUrl: './choose-subject.component.html',
  styleUrl: './choose-subject.component.css'
})
export class ChooseSubjectComponent {

  subjects$: Observable<Subject[]> | undefined
  id$ = this.userService.user$.pipe(map(user => user?.id))

  constructor(private http: HttpClient, private userService: UserService) {}

  ngOnInit(): void {
    console.log(this.id$)
    this.id$.subscribe(id => {
      this.http.get<{ success: boolean; message: string; data: any }>(`http://localhost:8080/usersteachsubjects/${id}`)
        .subscribe(
          {next: (response) => {
              console.log(`Subjects teached by ${id}`, response)
            },
          error: (error) => {
              console.log(`Error occured`, error)
          } },
        )
    })  
  }

}
