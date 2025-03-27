import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Student } from '../../utils/types/StudentInterface';
import { ApiResponse } from '../../utils/types/ApiResponseInterface';
import { AssignGrade } from '../../utils/types/AssignGradeInterface';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BaseLayoutComponent } from '../base-layout/base-layout.component';
import { DashboardHeaderComponent } from '../dashboard-header/dashboard-header.component';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-assign-grades',
  standalone: true,
  imports: [CommonModule, FormsModule, BaseLayoutComponent, DashboardHeaderComponent],
  templateUrl: './assign-grades.component.html',
  styleUrl: './assign-grades.component.css'
})

export class AssignGradesComponent {

  userTable: Student[] = []
  subjectId!: number
 constructor(private http: HttpClient, private route: ActivatedRoute) {}

 ngOnInit(): void {
  this.subjectId = Number(this.route.snapshot.paramMap.get('id'));
  this.http.get<ApiResponse<Student[]>>(`${environment.apiUrl}findusersbysubject/${this.subjectId}`).subscribe(
    response => {
      if(response.success){
        this.userTable = response.data
        console.log(`Students attending:`, this.userTable)
      } else {
        console.error(response.errors)
      }
    }
  )
}

assignGrade = (studentId: number, grade: number) => {
  const assignGradeDTO: AssignGrade = {
    userId: studentId,
    subjectId: this.subjectId,
    grade: grade
  }
  this.http.patch<ApiResponse<any>>(`${environment.apiUrl}assigngrade`, assignGradeDTO).subscribe(
    response => {
      if(response.success) {
        console.log(response.message)
      } else {
        console.log(response.errors)
      }
    }
  )
}

}
