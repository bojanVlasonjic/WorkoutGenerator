export class ExerciseDto {

    id: number;
    name: string;
    description: string;
    equipment: any;
    exerciseType: any;
    targetedMuscles: Array<number>;

    constructor() {
        this.targetedMuscles = [];
        this.exerciseType = 0;
        this.equipment = 0;
    }
}