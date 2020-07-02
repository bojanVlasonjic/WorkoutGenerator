import { Component, OnInit } from '@angular/core';
import { ExerciseDto } from 'src/app/dtos/exercise.dto';
import { ExerciseService } from 'src/app/services/exercise.service';
import { ExercisePreviewService } from 'src/app/services/exercise-preview.service';
import { ToasterService } from 'src/app/services/toaster.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-exercises',
  templateUrl: './view-exercises.component.html',
  styleUrls: ['./view-exercises.component.css']
})
export class ViewExercisesComponent implements OnInit {

  exercises: Array<ExerciseDto>;

  constructor(
    private router: Router,
    private exerciseService: ExerciseService,
    private exercisePreviewSvc: ExercisePreviewService,
    private toasterService: ToasterService
    ) {
    this.exercises = [];
  }

  ngOnInit() {
    this.exerciseService.getAllExercises().subscribe(
      data => {
        this.exercises = data;
      },
      err => {
        this.toasterService.showErrorMessage(err);
      }
    )
  }

  viewExercise(exercise: ExerciseDto): void {
    this.exercisePreviewSvc.sendExercise(exercise);
  }

  deleteExercise(exercise: ExerciseDto): void {

    if(!window.confirm(`Are you sure you want to delete ${exercise.name}?`)) {
      return;
    }

    let exerciseIndex = this.exercises.indexOf(exercise);
    this.exerciseService.deleteExercise(exercise.id).subscribe(
      data => {
        if (exerciseIndex > -1) {
          this.exercises.splice(exerciseIndex, 1);
        }
        this.toasterService.showMessage('Success', 'Exercise successfully removed');
      },
      err => {
        this.toasterService.showErrorMessage(err);
      }
    );
  }

  redirectToUpdate(exerciseId: number) {
    this.router.navigateByUrl(`/manage-exercise/${exerciseId}`);
  }


}
