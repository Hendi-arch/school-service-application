package com.hendi.schoolservice.infrastructure.config.db.schema;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.hendi.schoolservice.entity.submission.model.SubmissionModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "submissions")
public class SubmissionSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "assignment_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_assignment_id"), nullable = false)
    private AssignmentSchema assignment;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_student_id"), nullable = false)
    private UserSchema student;

    @Column(nullable = false)
    private Date submissionDate;

    @Column(nullable = false)
    private Float grade;

    @CreatedBy
    @Column(nullable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(nullable = false)
    private String updatedBy;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public SubmissionSchema(SubmissionModel submissionModel) {
        this.id = submissionModel.getId();
        this.student = new UserSchema(submissionModel.getStudent());
        this.assignment = new AssignmentSchema(submissionModel.getAssignment());
        this.grade = submissionModel.getGrade();
        this.submissionDate = submissionModel.getSubmissionDate();
        this.createdBy = submissionModel.getCreatedBy();
        this.updatedBy = submissionModel.getUpdatedBy();
        this.createdAt = submissionModel.getCreatedAt();
        this.updatedAt = submissionModel.getUpdatedAt();
    }

    public SubmissionModel toSubmissionModel() {
        SubmissionModel submissionModel = new SubmissionModel(
                this.assignment.toAssignmentModel(),
                this.student.toUserAccountModel(),
                this.submissionDate,
                this.grade);
        submissionModel.setId(this.id);
        submissionModel.setCreatedAt(this.createdAt);
        submissionModel.setUpdatedAt(this.updatedAt);
        submissionModel.setCreatedBy(this.createdBy);
        submissionModel.setUpdatedBy(this.updatedBy);
        return submissionModel;
    }

}
