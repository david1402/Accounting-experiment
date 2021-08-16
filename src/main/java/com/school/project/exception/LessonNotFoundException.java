package com.school.project.exception;

public class LessonNotFoundException extends RuntimeException {

    public LessonNotFoundException(String msg) {
        super(msg);
    }

}
