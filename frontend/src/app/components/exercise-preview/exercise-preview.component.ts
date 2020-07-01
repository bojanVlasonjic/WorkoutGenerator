import { Component, OnInit, Input } from '@angular/core';
import { ExerciseDto } from 'src/app/dtos/exercise.dto';
import { ExercisePreviewService } from 'src/app/services/exercise-preview.service';

@Component({
  selector: 'app-exercise-preview',
  templateUrl: './exercise-preview.component.html',
  styleUrls: ['./exercise-preview.component.css']
})
export class ExercisePreviewComponent implements OnInit {

  exercise: ExerciseDto;
  wrapperClass: string;
  modalClass: string;

  constructor(private exercisePreviewSvc: ExercisePreviewService) {
    this.hidePopUp();
  }

  ngOnInit() {

    this.exercisePreviewSvc.getExercise().subscribe(
      data => {
        this.displayExercise(data);
      },
      err => {
        console.log(err.error);
      }
    );

  }

  displayExercise(exercise: ExerciseDto): void {
    this.exercise = exercise;
    this.wrapperClass = 'modal-wrapper';
    this.modalClass = 'exercise-modal';
  }

  hidePopUp(): void {
    this.wrapperClass = 'hidden-content';
    this.modalClass = 'hidden-content';
  }

}
