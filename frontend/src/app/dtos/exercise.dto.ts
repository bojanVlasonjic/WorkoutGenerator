export class ExerciseDto {

    id: number;
    name: string;
    description: string;
    equipment: number;
    exerciseType: number;
    targetedMuscles: Array<number>;

    constructor() {
        this.targetedMuscles = [];
    }
}