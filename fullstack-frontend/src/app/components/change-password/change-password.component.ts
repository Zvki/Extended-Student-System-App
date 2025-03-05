import { Component, NgModule } from '@angular/core';
import { BaseLayoutComponent } from '../base-layout/base-layout.component';
import { FormsModule, NgForm } from '@angular/forms';
import { UserService } from '../../utils/UserService';
import { map } from 'rxjs';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { response } from 'express';
import { Router } from '@angular/router';

@Component({
  selector: 'app-change-password',
  standalone: true,
  imports: [BaseLayoutComponent, FormsModule, HttpClientModule],
  templateUrl: './change-password.component.html',
  styleUrl: './change-password.component.css'
})
export class ChangePasswordComponent {

  constructor(private router: Router, private http: HttpClient, private userService: UserService) {}

  oldPassword: string = '';
  newPassword: string = '';
  confirmPassword: string = '';

  id$ = this.userService.user$.pipe(map(user => user?.id));

  onChangePassword(): void {
    
    if (this.newPassword !== this.confirmPassword) {
      alert('New passwords do not match!');
      return;
    }

    const requestBody = {
      currentPasswd: this.oldPassword,
      newPasswd: this.newPassword,
      newPasswdConfirmation: this.confirmPassword
    }

    this.id$.subscribe( id => {
      this.http.patch(`http://localhost:8080/changepasswd/${id}`, requestBody).subscribe({
        next: (response) => {
          alert('Password changed succesfully');
          this.router.navigate(['/profile'])
        }, error: (error) => {
          alert('Error changing password')
          console.error(error)
        }
      })
    }
    )
  }
}
