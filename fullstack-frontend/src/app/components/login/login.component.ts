import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { UserService } from '../../utils/UserService';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, HttpClientModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  isLoading: boolean = false;
  email: string = '';
  password: string = '';

  constructor(private http: HttpClient, private router: Router, private userService: UserService) {}

  onSubmit(): void {
    this.isLoading = true;

    if (this.isLoading) {
      this.router.navigate(['/loading']); 
    }

    const loginData = { email: this.email, password: this.password };

    setTimeout(() => {
    this.http.post<{ success: boolean; message: string; data: any }>('http://localhost:8080/login', loginData, { withCredentials: true })
      .subscribe({
        next: (response) => {
          console.log('Login successful:', response);
          
          if (response.success && response.data) {
            this.userService.setUser(response.data);
          }
          
          this.isLoading = false;
          this.router.navigate(['']);
        },
        error: (error) => {
          console.error('Login failed:', error);
          this.isLoading = false;
          this.router.navigate(['/login']);
        }
      });
    }, 1500);
}

}
