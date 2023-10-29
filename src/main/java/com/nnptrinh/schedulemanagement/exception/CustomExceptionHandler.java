package com.nnptrinh.schedulemanagement.exception;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseObject handleAllException(Exception ex) {
        log.error("Exception: " + ex.getMessage() + " with type: " + ex.getClass());
        log.error(Arrays.toString(ex.getStackTrace()));
        return new ResponseObject(ex.getMessage(), null, false);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseObject handleAccessDeniedException(AccessDeniedException ex) {
        log.error("Exception: " + ex.getMessage() + " with type: " + ex.getClass());
        log.error(Arrays.toString(ex.getStackTrace()));
        return new ResponseObject("You do not have permission for this action", null, false);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseObject handleRequestException(IllegalArgumentException ex) {
        log.error("Exception: " + ex.getMessage() + " with type: " + ex.getClass());
        log.error(Arrays.toString(ex.getStackTrace()));
        return new ResponseObject(ex.getMessage(), null, false);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseObject handleArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        log.error("Exception: " + ex.getMessage() + " with type: " + ex.getClass());
        String errorMessage =
                ex.getBindingResult().getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining(", "))
                        + ".";
        log.error(Arrays.toString(ex.getStackTrace()));
        return new ResponseObject(errorMessage, null, false);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseObject handleRequestException(EntityNotFoundException ex) {
        log.error("Exception: " + ex.getMessage() + " with type: " + ex.getClass());
        log.error(Arrays.toString(ex.getStackTrace()));
        return new ResponseObject(ex.getMessage(), null, false);
    }

    @ExceptionHandler({ExpiredJwtException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseObject forbiddenException(ExpiredJwtException ex) {
        log.error("Exception: " + ex.getMessage() + " with type: " + ex.getClass());
        log.error(Arrays.toString(ex.getStackTrace()));
        return new ResponseObject(ex.getMessage(), null, false);
    }

}