import { ConditioningWorkoutDto } from './conditioning-workout.dto';
import { StrengthWorkoutDto } from './strength-workout.dto';
import { ReviewDto } from './review.dto';

export class WorkoutDto {

    id: number;
    userId: number;

    conditioningWorkoutDto: ConditioningWorkoutDto;
    strengthWorkoutDto: StrengthWorkoutDto;

    date: Date;
    review: ReviewDto;

    constructor() {
        this.conditioningWorkoutDto = new ConditioningWorkoutDto();
        this.strengthWorkoutDto = new StrengthWorkoutDto();
    }
}