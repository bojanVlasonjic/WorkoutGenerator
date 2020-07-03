import { Component, OnInit, SimpleChanges, OnDestroy, Output, EventEmitter } from '@angular/core';
import { WorkoutDto } from 'src/app/dtos/workout.dto';
import { ReviewDto } from 'src/app/dtos/review.dto';
import { ReviewService } from 'src/app/services/review.service';
import { WorkoutReviewObservService } from 'src/app/services/workout-review-observ.service';
import { Subscription } from 'rxjs';
import { ToasterService } from 'src/app/services/toaster.service';

@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.component.html',
  styleUrls: ['./add-review.component.css']
})
export class AddReviewComponent implements OnInit, OnDestroy {

  workout: WorkoutDto;
  review: ReviewDto;

  workoutSubscription: Subscription;

  @Output() reviewEmitter = new EventEmitter<WorkoutDto>();

  constructor(
    private reviewService: ReviewService,
    private workoutReviewSvc: WorkoutReviewObservService,
    private toasterService: ToasterService
    ) {
      this.review = new ReviewDto();
  }

  ngOnInit() {

    this.workoutSubscription = this.workoutReviewSvc.getWorkout().subscribe(
      data => {
        this.workout = data;
        this.review.workoutId = this.workout.id;
        this.review.userId = this.workout.userId;
      },
      err => {
        console.log(err);
      }
    );

  }

  ngOnDestroy() {
    this.workoutSubscription.unsubscribe();
  }

  hidePopUp(): void {
    this.workout = null;
  }

  increaseExertion(): void {
    if (this.review.exertionLevel < 10) {
      this.review.exertionLevel += 1;
    }
  }

  decreaseExertion(): void {
    if (this.review.exertionLevel > 1) {
      this.review.exertionLevel -= 1;
    }
  }

  increaseRating(): void {
    if (this.review.rating < 10) {
      this.review.rating += 1;
    }
  }

  decreaseRating(): void {
    if (this.review.rating > 1) {
      this.review.rating -= 1;
    }
  }

  complaintChecked(complaintId: number) {
    
    let index = this.review.complaints.indexOf(complaintId);

    if(index > -1) {
      this.review.complaints.splice(index, 1); // remove the item if it is already added
    } else {
      this.review.complaints.push(complaintId); // add the complaint
    }
  }

  addReview(): void {

    this.reviewService.createReview(this.review).subscribe(
      data => {
        this.workout.review = data;
        this.reviewEmitter.emit(this.workout);
        this.hidePopUp();
      },
      err => {
        this.toasterService.showErrorMessage(err);
      }
    );

  }



}
