import { HttpClient, HttpClientModule} from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { BaseLayoutComponent } from '../base-layout/base-layout.component';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [HttpClientModule, FormsModule, BaseLayoutComponent],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  isLoading: boolean = false;

  registerData = {
    name: '',
    surname: '',
    email: '',
    password: '',
    passwordConfirmation: ''
  };

  constructor(private http: HttpClient, private router: Router) {}

  onRegister() {

    this.isLoading = true;
    if(this.isLoading) {
      this.router.navigate(['/loading']);
    }

    setTimeout(() => {
    this.http.post<{ success: boolean; message: string; data: any }>('http://localhost:8080/register', this.registerData)
    .subscribe({ next: (response) => {
      console.log('Register successful:', response);
      
      this.isLoading = false;
      this.router.navigate(['']);

    }, error: (error) => {
      console.error('Register failed:', error);
      this.isLoading = false;
      this.router.navigate(['/register']);
    }});
  }, 1500);

  }
}
