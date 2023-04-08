package com.challenge.challenge.Errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
/**
 * Ispired by:
 * https://www.baeldung.com/exception-handling-for-rest-with-spring
 * There are many ways to implement error handling in Spring, I chose ControllerAdviced to have
 * a global class to handle my errors
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> appointmentNotFoundException(NotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }
}
