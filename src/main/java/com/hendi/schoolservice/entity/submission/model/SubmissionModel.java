package com.hendi.schoolservice.entity.submission.model;

import java.time.LocalDateTime;
import java.util.Date;

import com.hendi.schoolservice.entity.AbstractEntity;
import com.hendi.schoolservice.entity.assignment.model.AssignmentModel;
import com.hendi.schoolservice.entity.user.model.UserAccountModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class SubmissionModel extends AbstractEntity<Long> {

    public SubmissionModel(
            AssignmentModel assignment,
            UserAccountModel student,
            Date submissionDate,
            Float grade) {
        this.assignment = assignment;
        this.student = student;
        this.submissionDate = submissionDate;
        this.grade = grade;
    }

    private AssignmentModel assignment;

    private UserAccountModel student;

    private Date submissionDate;

    private Float grade;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
