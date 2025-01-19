import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../utils/AuthService';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [FormsModule, HttpClientModule],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css'
})
export class LoginPageComponent {

  email: string = '';
  password: string = '';

  constructor(private http: HttpClient) {}

  onSubmit(): void {
    const loginData = { email: this.email, password: this.password };

    this.http.post('http://localhost:8080/login', loginData).subscribe({
      next: (response) => {
        console.log('Login successful:', response);
      },
      error: (error) => {
        console.error('Login failed:', error);
      }
    });
  }

}
