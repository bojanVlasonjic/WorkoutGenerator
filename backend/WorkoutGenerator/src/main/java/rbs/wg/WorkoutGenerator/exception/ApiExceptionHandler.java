package rbs.wg.WorkoutGenerator.exception;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<JsonNode> handleException(ApiException ex) {
        return new ResponseEntity<>(ex.getExceptionJson(), ex.getStatus());
    }

}
