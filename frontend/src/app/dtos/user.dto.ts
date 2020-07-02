
export class UserDto {

    id: number;
    email: string;
    password: string;
    name: string;
    weight: number;
    userLevel: number;
    isBanned: boolean;

    repetitionFactor: number;
    workLoadFactor: number;
    workIntervalFactor: number;


    constructor() {
    }
}