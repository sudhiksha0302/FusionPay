package com.coforge.training.userservice.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<?> handleAlreadyExists(ResourceAlreadyExistsException ex){

        Map<String,Object> map=new HashMap<>();
        map.put("timestamp", LocalDateTime.now());
        map.put("status",409);
        map.put("message", ex.getMessage());

        return new ResponseEntity<>(map,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFound(ResourceNotFoundException ex){

        Map<String,Object> map=new HashMap<>();
        map.put("timestamp", LocalDateTime.now());
        map.put("status",404);
        map.put("message", ex.getMessage());

        return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validation(MethodArgumentNotValidException ex){

        Map<String,String> errors=new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error->errors.put(error.getField(),error.getDefaultMessage()));

        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handle(Exception ex){

        Map<String,Object> map=new HashMap<>();
        map.put("timestamp", LocalDateTime.now());
        map.put("status",500);
        map.put("message", ex.getMessage());

        return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}