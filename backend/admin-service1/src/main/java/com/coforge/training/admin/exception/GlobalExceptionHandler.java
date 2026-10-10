package com.coforge.training.admin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AdminNotFoundException.class)
    public ResponseEntity<String> handleAdminNotFound(AdminNotFoundException ex){

        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidation(MethodArgumentNotValidException ex){

        String error = ex.getBindingResult().getFieldError().getDefaultMessage();

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex){

        return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

    }

}