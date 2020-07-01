import { Component, OnInit } from '@angular/core';
import { WorkoutRequestDto } from 'src/app/dtos/workout-request.dto';
import { WorkoutService } from 'src/app/services/workout.service';
import { ToasterService } from 'src/app/services/toaster.service';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { WorkoutDto } from 'src/app/dtos/workout.dto';

@Component({
  selector: 'app-generate-workout',
  templateUrl: './generate-workout.component.html',
  styleUrls: ['./generate-workout.component.css']
})
export class GenerateWorkoutComponent implements OnInit {

  isWorkoutGenerating: boolean;
  isWorkoutBeingSaved: boolean;

  workoutRequest: WorkoutRequestDto;
  workout: WorkoutDto;

  constructor(
    private workoutService: WorkoutService,
    private toasterService: ToasterService,
    private authService: AuthenticationService
    ) { 
    this.workoutRequest = new WorkoutRequestDto();

    this.isWorkoutGenerating = false;
    this.isWorkoutBeingSaved = false;
  }

  ngOnInit() {
    this.workoutRequest.email = this.authService.getCurrentUser().email;
  }

  equipmentChecked(equipmentId: number): void {
    
    let index = this.workoutRequest.specifiedEquipment.indexOf(equipmentId);

    if(index > -1) {
      this.workoutRequest.specifiedEquipment.splice(index, 1); // remove the item if it is already added
    } else {
      this.workoutRequest.specifiedEquipment.push(equipmentId); // add the selected equipment
    }

  }

  generateWorkout() {
    
    this.isWorkoutGenerating = true;
    this.workoutService.generateWorkout(this.workoutRequest).subscribe(
      data => {
        this.workout = data;
      },
      err => {
        this.toasterService.showErrorMessage(err);
      }
    ).add(
      () => { this.isWorkoutGenerating = false; }
    );
  }

  saveWorkout() {

    this.isWorkoutBeingSaved = true;
    this.workoutService.saveWorkout(this.workout).subscribe(
      data => {
        this.workout = null;
        this.toasterService.showMessage('Success', 'Workout saved');
      },
      err => {
        this.toasterService.showErrorMessage(err);
      }
    ).add(
      () => { this.isWorkoutBeingSaved = false; }
    );
  }

}
