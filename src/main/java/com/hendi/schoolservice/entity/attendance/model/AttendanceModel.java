package com.hendi.schoolservice.entity.attendance.model;

import java.time.LocalDateTime;
import java.util.Date;

import com.hendi.schoolservice.entity.AbstractEntity;
import com.hendi.schoolservice.entity.classroom.model.ClassroomModel;
import com.hendi.schoolservice.entity.user.model.UserAccountModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class AttendanceModel extends AbstractEntity<Long> {

    public AttendanceModel(
            UserAccountModel student,
            ClassroomModel classroom,
            Date date,
            Boolean isPresent) {
        this.student = student;
        this.classroom = classroom;
        this.date = date;
        this.isPresent = isPresent;
    }

    private UserAccountModel student;

    private ClassroomModel classroom;

    private Date date;

    private Boolean isPresent;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
