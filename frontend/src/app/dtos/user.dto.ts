
export class UserDto {

    id: number;
    email: string;
    password: string;
    firstName: string;
    lastName: string;
    weight: number;
    userLevel: number;

    repetitionFactor: number;
    workLoadFactor: number;
    workIntervalFactor: number;


    constructor() {
    }
}