import { Component, OnInit, Input } from '@angular/core';
import { WorkoutDto } from 'src/app/dtos/workout.dto';
import { ExercisePreviewService } from 'src/app/services/exercise-preview.service';
import { ExerciseDto } from 'src/app/dtos/exercise.dto';

@Component({
  selector: 'app-workout-preview',
  templateUrl: './workout-preview.component.html',
  styleUrls: ['./workout-preview.component.css']
})
export class WorkoutPreviewComponent implements OnInit {

  @Input() workout: WorkoutDto;

  constructor(private exercisePreviewSvc: ExercisePreviewService) { 
  }

  ngOnInit() {
  }

  displayExercise(exercise: ExerciseDto) {
    this.exercisePreviewSvc.sendExercise(exercise);
  }


}
