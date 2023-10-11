package com.nnptrinh.schedulemanagement.exception;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseObject handleAllException(Exception ex) {
        return new ResponseObject(ex.getMessage(), null, false);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseObject handleRequestException(IllegalArgumentException ex) {
        return new ResponseObject(ex.getMessage(), null, false);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseObject handleRequestException(EntityNotFoundException ex) {
        return new ResponseObject(ex.getMessage(), null, false);
    }

    @ExceptionHandler({ExpiredJwtException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseObject forbiddenException(ExpiredJwtException ex) {
        return new ResponseObject(ex.getMessage(), null, false);
    }

}