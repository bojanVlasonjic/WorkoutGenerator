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
    return window.localStorage.getItem('user') != null;
  }

  getCurrentUser(): AuthenticationResponseDto {
    return JSON.parse(window.localStorage.getItem('user'));
  }

  rememberUser(authResponse: AuthenticationResponseDto): void {
    window.localStorage.setItem('user', JSON.stringify(authResponse));
  }

  clearUser(): void {
    window.localStorage.removeItem('user');
  }


}
