import { Component, OnInit, ViewChild } from '@angular/core';
import { ExerciseDto } from 'src/app/dtos/exercise.dto';
import { ExerciseService } from 'src/app/services/exercise.service';
import { ToasterService } from 'src/app/services/toaster.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-manage-exercise',
  templateUrl: './manage-exercise.component.html',
  styleUrls: ['./manage-exercise.component.css']
})
export class ManageExerciseComponent implements OnInit {

  exercise: ExerciseDto;
  isEditing: boolean;
  @ViewChild('exForm', {static: false}) exerciseForm: any;

  constructor(
    private route: ActivatedRoute,
    private exerciseService: ExerciseService,
    private toasterService: ToasterService
    ) {
    this.exercise = new ExerciseDto();
  }

  ngOnInit() {
    this.route.params.subscribe(
      params => {
        if (params['id'] !== undefined) {
          this.loadExercise(params['id']);
          this.isEditing = true;
        } else {
          this.isEditing = false;
        }
      }
    );
  }

  muscleGroupChecked(muscleGroupId: number) {

    let index = this.exercise.targetedMuscles.indexOf(muscleGroupId);

    if(index > -1) {
      this.exercise.targetedMuscles.splice(index, 1); // remove the item if it is already added
    } else {
      this.exercise.targetedMuscles.push(muscleGroupId); // add the muscle group
    }

  }

  loadExercise(id: number) {

    this.exerciseService.getExerciseById(id).subscribe(
      data => {
        this.exercise = data;
        this.updateCheckBoxes(this.exercise.targetedMuscles);
      },
      err => {
        this.toasterService.showErrorMessage(err);
      }
    );

  }

  manageExercise(): void {

    if (!this.exerciseForm.valid || this.exercise.targetedMuscles.length == 0) {
      return;
    }

    if(this.isEditing) {
      this.updateExercise();
    } else {
      this.createExercise();
    }

  }

  updateExercise(): void {
    this.exerciseService.updateExercise(this.exercise).subscribe(
      data => {
        this.exercise = data;
       this.toasterService.showMessage('Success', 'Exercise successfully updated');
      },
      err => {
        this.toasterService.showErrorMessage(err);
      }
    );
  }

  createExercise(): void {
    this.exerciseService.createExercise(this.exercise).subscribe(
      data => {
       this.exerciseForm.reset();
       this.toasterService.showMessage('Success', 'Exercise successfully created');
      },
      err => {
        this.toasterService.showErrorMessage(err);
      }
    );
  }

  updateCheckBoxes(muscleGroups: Array<any>) {

    muscleGroups.forEach(
      mg => {
        document.getElementById(mg.toLowerCase() + 'Check').setAttribute('checked', 'true');
      }
    );
  }

}
