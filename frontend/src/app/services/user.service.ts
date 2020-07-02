import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserDto } from '../dtos/user.dto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }


  getAllUsers(): Observable<any> {
    return this.http.get('api/user');
  }

  getUserByEmail(email: string): Observable<any> {
    return this.http.get(`api/user/${email}`);
  }

  createNewUser(userData: UserDto): Observable<any> {
    return this.http.post('api/user', userData);
  }

  changeUserStatus(userId: number): Observable<any> {
    return this.http.put(`api/user/status/${userId}`, null);
  }
  
}
