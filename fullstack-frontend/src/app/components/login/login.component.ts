import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, HttpClientModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  email: string = '';
  password: string = '';

  constructor(private http: HttpClient) {}

  onSubmit(): void {
    const loginData = { email: this.email, password: this.password };

    this.http.post('http://localhost:8080/login', loginData, { withCredentials: true }).subscribe({
      next: (response) => {
        console.log('Login successful:', response);
      },
      error: (error) => {
        console.error('Login failed:', error);
      }
    });
  }

}
