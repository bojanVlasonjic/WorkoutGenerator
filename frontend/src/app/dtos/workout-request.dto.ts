export class WorkoutRequestDto {

    email: string;
    specifiedEquipment: Array<number>;
    workoutType: number;

    constructor() {
        this.specifiedEquipment = [];
        this.workoutType = 0;
    }

}