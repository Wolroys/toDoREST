package com.wolroys.todorest.validation.handler;

import com.wolroys.todorest.validation.exception.UsernameExistError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
    }

    @ExceptionHandler(UsernameExistError.class)
    public ResponseEntity<String> handleUsernameException(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This username is already taken");
    }

}
