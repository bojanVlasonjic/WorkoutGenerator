package rbs.wg.WorkoutGenerator.exception;

import org.springframework.http.HttpStatus;

public class ApiBadRequestException extends ApiException {

    public ApiBadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
