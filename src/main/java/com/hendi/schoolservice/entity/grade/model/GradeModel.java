package com.hendi.schoolservice.entity.grade.model;

import java.time.LocalDateTime;

import com.hendi.schoolservice.entity.AbstractEntity;
import com.hendi.schoolservice.entity.classroom.model.ClassroomModel;
import com.hendi.schoolservice.entity.user.model.UserAccountModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class GradeModel extends AbstractEntity<Long> {

    public GradeModel(
            UserAccountModel student,
            ClassroomModel classroom,
            Float grade) {
        this.student = student;
        this.classroom = classroom;
        this.grade = grade;
    }

    private UserAccountModel student;

    private ClassroomModel classroom;

    private Float grade;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
