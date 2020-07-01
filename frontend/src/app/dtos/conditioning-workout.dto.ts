import { ExerciseDto } from './exercise.dto';

export class ConditioningWorkoutDto {

    id: number;
    workoutId: number;

    workInterval: number;
    restInterval: number;
    numberOfIntervals: number;

    numberOfRounds: number;
    restBetweenRounds: number;

    exercises: Array<ExerciseDto>;

    constructor() {
        this.exercises = [];
    }
}