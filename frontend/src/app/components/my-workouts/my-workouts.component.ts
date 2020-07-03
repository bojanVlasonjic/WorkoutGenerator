import { Component, OnInit } from '@angular/core';
import { WorkoutDto } from 'src/app/dtos/workout.dto';
import { WorkoutService } from 'src/app/services/workout.service';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { ToasterService } from 'src/app/services/toaster.service';
import { WorkoutReviewObservService } from 'src/app/services/workout-review-observ.service';


@Component({
  selector: 'app-my-workouts',
  templateUrl: './my-workouts.component.html',
  styleUrls: ['./my-workouts.component.css']
})

export class MyWorkoutsComponent implements OnInit {

  userWorkouts: Array<WorkoutDto>;

  constructor(
    private workoutService: WorkoutService,
    private authService: AuthenticationService,
    private toasterService: ToasterService,
    private workoutReviewSvc: WorkoutReviewObservService

  ) {
    this.userWorkouts = [];
  }

  ngOnInit() {
    this.getUserWorkouts();
  }

  getUserWorkouts(): void {

    this.workoutService.getUserWorkouts(this.authService.getCurrentUser().email).subscribe(
      data => {
        console.log(data);
        this.userWorkouts = data;
      },
      err => {
        this.toasterService.showErrorMessage(err);
      }
    );
  }

  toggleReviewDialog(workout: WorkoutDto) {
    this.workoutReviewSvc.sendWorkout(workout);
  }

  receiveReview($event): void {
    let workoutIndex = this.userWorkouts.indexOf($event);

    if (workoutIndex > -1) {
      this.userWorkouts[workoutIndex] = $event;
    }
  }

}
