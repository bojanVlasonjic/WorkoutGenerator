import { Component, OnInit, Input } from '@angular/core';
import { WorkoutDto } from 'src/app/dtos/workout.dto';
import { ExercisePreviewObservService } from 'src/app/services/exercise-preview-observ.service';
import { ExerciseDto } from 'src/app/dtos/exercise.dto';

@Component({
  selector: 'app-workout-preview',
  templateUrl: './workout-preview.component.html',
  styleUrls: ['./workout-preview.component.css']
})
export class WorkoutPreviewComponent implements OnInit {

  @Input() workout: WorkoutDto;

  constructor(private exercisePreviewSvc: ExercisePreviewObservService) { 
  }

  ngOnInit() {
  }

  displayExercise(exercise: ExerciseDto) {
    this.exercisePreviewSvc.sendExercise(exercise);
  }


}
