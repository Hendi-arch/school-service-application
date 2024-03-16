package com.hendi.schoolservice.entity.usertoken.exception;

public class UserTokenNotFoundException extends RuntimeException {

    public UserTokenNotFoundException() {
        super("User token not found in the system");
    }

}
