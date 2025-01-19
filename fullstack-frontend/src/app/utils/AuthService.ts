import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private loginUrl = 'http://localhost:8080/login'; // Adres endpointu backendu

  constructor(private http: HttpClient) {}

  login(loginData: { email: string; password: string }): Observable<any> {
    return this.http.post(this.loginUrl, loginData);
  }
}
