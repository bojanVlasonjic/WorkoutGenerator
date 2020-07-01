import { StrengthRegimeDto } from './strength-regime.dto';

export class StrengthWorkoutDto {

    id: number;
    workoutId: number;
    restBetweenSets: number;

    strengthRegimes: Array<StrengthRegimeDto>;

    constructor() {
        this.strengthRegimes = [];
    }
}