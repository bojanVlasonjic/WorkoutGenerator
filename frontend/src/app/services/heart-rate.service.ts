import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { HeartRateDto } from '../dtos/heart-rate.dto';

@Injectable({
  providedIn: 'root'
})
export class HeartRateService {

  constructor(private http: HttpClient) { }

  startSimulation(email: string): Observable<any> {
    return this.http.get(`api/heart-rate/start/${email}`);
  }

  sendHeartRate(heartRateDto: HeartRateDto): Observable<any> {
    return this.http.post('api/heart-rate', heartRateDto);
  }

  stopSimulation(email: string): Observable<any> {
    return this.http.get(`api/heart-rate/stop/${email}`);
  }
}
