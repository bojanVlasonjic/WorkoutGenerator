import { Injectable } from '@angular/core';
import { WorkoutDto } from '../dtos/workout.dto';
import { Subject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WorkoutReviewObservService {

  private subject: Subject<WorkoutDto>;

  constructor() { 
    this.subject = new Subject<WorkoutDto>();
  }

  sendWorkout(workout: WorkoutDto): void {
    this.subject.next(workout);
  }

  getWorkout(): Observable<WorkoutDto> {
    return this.subject.asObservable();
  }
}
