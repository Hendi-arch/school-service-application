package com.hendi.schoolservice.entity.course.exception;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException() {
        super("Course not found in the system");
    }

}
