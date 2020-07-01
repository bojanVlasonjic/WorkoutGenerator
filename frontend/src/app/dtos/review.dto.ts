
export class ReviewDto {

    id: number;
    workoutId: number;
    userId: number;

    exertionLevel: number;
    rating: number;

    complaints: Array<number>;

    constructor() {
        this.complaints = [];
    }

}