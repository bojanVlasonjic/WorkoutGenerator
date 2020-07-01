import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { WorkoutDto } from '../dtos/workout.dto';
import { WorkoutRequestDto } from '../dtos/workout-request.dto';

@Injectable({
  providedIn: 'root'
})
export class WorkoutService {

  constructor(private http: HttpClient) { }


  getUserWorkouts(userEmail: string): Observable<any> {
    return this.http.get(`api/workout/${userEmail}`);
  }

  saveWorkout(workout: WorkoutDto): Observable<any> {
    return this.http.post('api/workout', workout);
  }

  generateWorkout(workoutRequest: WorkoutRequestDto): Observable<any> {
    return this.http.post('api/workout/generate', workoutRequest);
  }
}
