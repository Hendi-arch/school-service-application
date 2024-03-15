package com.hendi.schoolservice.entity.user.exception;

public class PasswordNotMatchException extends RuntimeException {

    public PasswordNotMatchException() {
        super("Passwords do not match.");
    }

}
