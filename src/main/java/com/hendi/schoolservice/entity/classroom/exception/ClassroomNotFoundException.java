package com.hendi.schoolservice.entity.classroom.exception;

public class ClassroomNotFoundException extends RuntimeException {

    public ClassroomNotFoundException() {
        super("Classroom not found in the system");
    }

}
