package ro.fasttrackit.homeWork9.controller.exception;

import lombok.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class RoomControllerAdvice {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(BAD_REQUEST)
    ApiError handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ApiError(ex.getMessage());
    }
}

@Value
class ApiError {
    String message;
}