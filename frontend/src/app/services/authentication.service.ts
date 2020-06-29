import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthenticationRequestDto } from '../dtos/authentication-request.dto';
import { Observable } from 'rxjs';
import { AuthenticationResponseDto } from '../dtos/authentication-response.dto';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient) { }

  authenticate(authRequest: AuthenticationRequestDto): Observable<any> {
    return this.http.post('api/authentication', authRequest);
  }

  isUserSignedIn(): boolean {
    return window.localStorage.getItem('token') != null &&
      window.localStorage.getItem('role') != null; 
  }

  rememberUser(authResponse: AuthenticationResponseDto): void {
    window.localStorage.setItem('token', authResponse.jwt);
    window.localStorage.setItem('role', authResponse.role);
  }

  clearUser(): void {
    window.localStorage.removeItem('token');
    window.localStorage.removeItem('role');
  }


}
