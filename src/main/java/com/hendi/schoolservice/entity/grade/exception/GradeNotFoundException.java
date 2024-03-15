package com.hendi.schoolservice.entity.grade.exception;

public class GradeNotFoundException extends RuntimeException {

    public GradeNotFoundException() {
        super("Grade not found in the system");
    }

}
