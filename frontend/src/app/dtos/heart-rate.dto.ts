export class HeartRateDto {

    heartRate: number;
    goal: number;

    userEmail: string;
    notificationMessage: string;

    constructor(email: string) {
        this.userEmail = email;
    }
}