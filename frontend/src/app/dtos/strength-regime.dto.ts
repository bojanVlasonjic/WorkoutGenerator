import { ExerciseDto } from './exercise.dto';

export class StrengthRegimeDto {

    id: number;
    strengthWorkoutId: number;

    exerciseDto: ExerciseDto;

    repetitions: number;
    numberOfSets: number;
    workLoad: number;

    constructor() {
        this.exerciseDto = new ExerciseDto();
    }
}