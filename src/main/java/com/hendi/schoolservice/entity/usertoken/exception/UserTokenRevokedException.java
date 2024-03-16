package com.hendi.schoolservice.entity.usertoken.exception;

public class UserTokenRevokedException extends RuntimeException {
    
    public UserTokenRevokedException() {
        super("Unauthorized: Authentication failed or token revoked.");
    }

}
