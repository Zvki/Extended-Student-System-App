import { Component } from '@angular/core';
import { BaseLayoutComponent } from '../base-layout/base-layout.component';
import { UserService } from '../../utils/UserService';

import { FormsModule } from '@angular/forms';
import { map } from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { response } from 'express';
import { Router } from '@angular/router';
import { error } from 'console';

@Component({
  selector: 'app-edit-profile',
  standalone: true,
  imports: [BaseLayoutComponent, FormsModule, AsyncPipe],
  templateUrl: './edit-profile.component.html',
  styleUrl: './edit-profile.component.css'
})
export class EditProfileComponent {

  name: string = '';
  surname: string = '';
  email: string = '';
  id: number = 0;

  constructor(private userService: UserService, private http: HttpClient, private router: Router) {}

  ngOnInit(){
    this.userService.user$.subscribe( user => {
      this.name = user?.name || '';
      this.surname = user?.surname || '';
      this.email = user?.email || '';
      this.id = user?.id || 0;
    }
    )
  }

  onSaveProfile() {
    
    const requestBody = {
      name: this.name,
      surname: this.surname,
      email: this. email
    }

    this.http.patch<{ success: boolean; message: string; data: any }>(`http://localhost:8080/editprofile/${this.id}`, requestBody).subscribe({
      next: (response) => {

        if (response.success && response.data) {
          this.userService.setUser(response.data);
        }

        console.log(response.data)
        
        alert('Profile updated successfully')
        this.router.navigate(["/profile"])
      },
      error: (error) => {
        alert('Error occured')
        console.log(error)
      }
    })
  }
}
