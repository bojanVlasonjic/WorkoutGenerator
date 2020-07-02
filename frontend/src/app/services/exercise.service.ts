import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ExerciseDto } from '../dtos/exercise.dto';

@Injectable({
  providedIn: 'root'
})
export class ExerciseService {

  constructor(private http: HttpClient) { }

  getAllExercises(): Observable<any> {
    return this.http.get('api/exercise');
  }

  getExerciseById(exerciseId: number): Observable<any> {
    return this.http.get(`api/exercise/${exerciseId}`);
  } 

  createExercise(exercise: ExerciseDto): Observable<any> {
    return this.http.post('api/exercise', exercise);
  }

  updateExercise(exercise: ExerciseDto): Observable<any> {
    return this.http.put('api/exercise', exercise);
  }

  deleteExercise(exerciseId: number): Observable<any> {
    return this.http.delete(`api/exercise/${exerciseId}`);
  }
}
