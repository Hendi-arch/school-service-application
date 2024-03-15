package com.hendi.schoolservice.entity.attendance.exception;

public class AttendanceNotFoundException extends RuntimeException {

    public AttendanceNotFoundException() {
        super("Attendance not found in the system");
    }

}
