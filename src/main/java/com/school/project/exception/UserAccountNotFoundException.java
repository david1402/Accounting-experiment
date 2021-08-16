package com.school.project.exception;

public class UserAccountNotFoundException extends RuntimeException {

    public UserAccountNotFoundException(String msg) {
        super(msg);
    }

}
