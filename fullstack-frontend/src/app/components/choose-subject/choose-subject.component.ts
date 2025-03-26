import { Component } from '@angular/core';
import { UserService } from '../../utils/UserService';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-choose-subject',
  standalone: true,
  imports: [],
  templateUrl: './choose-subject.component.html',
  styleUrl: './choose-subject.component.css'
})
export class ChooseSubjectComponent {



  constructor(private http: HttpClient, private userService: UserService) {}

}
