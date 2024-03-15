package com.hendi.schoolservice.entity.submission.exception;

public class SubmissionNotFoundException extends RuntimeException {

    public SubmissionNotFoundException() {
        super("Submission not found in the system");
    }

}
