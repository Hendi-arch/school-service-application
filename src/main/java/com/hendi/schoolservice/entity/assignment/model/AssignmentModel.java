package com.hendi.schoolservice.entity.assignment.model;

import java.time.LocalDateTime;
import java.util.Date;

import com.hendi.schoolservice.entity.AbstractEntity;
import com.hendi.schoolservice.entity.classroom.model.ClassroomModel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class AssignmentModel extends AbstractEntity<Long> {

    public AssignmentModel(
            ClassroomModel classroom,
            String assignmentName,
            Date dueDate,
            Float maxPoints) {
        this.classroom = classroom;
        this.assignmentName = assignmentName;
        this.dueDate = dueDate;
        this.maxPoints = maxPoints;
    }

    private ClassroomModel classroom;

    private String assignmentName;

    private Date dueDate;

    private Float maxPoints;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
