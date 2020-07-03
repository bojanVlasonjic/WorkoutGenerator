import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';
import { ExerciseDto } from '../dtos/exercise.dto';

@Injectable({
  providedIn: 'root'
})
export class ExercisePreviewObservService {

  private exerciseSubject: Subject<ExerciseDto>;

  constructor() { 
    this.exerciseSubject = new Subject<ExerciseDto>();
  }

  sendExercise(exercise: ExerciseDto): void {
    this.exerciseSubject.next(exercise);
  }

  getExercise(): Observable<ExerciseDto> {
    return this.exerciseSubject.asObservable();
  }
}
