package com.wolroys.todorest.validation.exception;

public class UsernameExistError extends RuntimeException{

    public UsernameExistError(String message) {
        super(message);
    }
}
