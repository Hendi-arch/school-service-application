package com.hendi.schoolservice.entity.assignment.exception;

public class AssignmentNotFoundException extends RuntimeException {

    public AssignmentNotFoundException() {
        super("Assignment not found in the system");
    }
    
}
