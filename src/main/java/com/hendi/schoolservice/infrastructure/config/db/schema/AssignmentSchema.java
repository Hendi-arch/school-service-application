package com.hendi.schoolservice.infrastructure.config.db.schema;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.hendi.schoolservice.entity.assignment.model.AssignmentModel;

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

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "assignments")
public class AssignmentSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "classroom_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_classroom_id"), nullable = false)
    private ClassroomSchema classroom;

    @Column(nullable = false)
    private String assignmentName;

    @Column(nullable = false)
    private Date dueDate;

    @Column(nullable = false)
    private Float maxPoints;

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

    public AssignmentSchema(AssignmentModel assignmentModel) {
        this.id = assignmentModel.getId();
        this.classroom = new ClassroomSchema(assignmentModel.getClassroom());
        this.assignmentName = assignmentModel.getAssignmentName();
        this.dueDate = assignmentModel.getDueDate();
        this.maxPoints = assignmentModel.getMaxPoints();
        this.createdBy = assignmentModel.getCreatedBy();
        this.updatedBy = assignmentModel.getUpdatedBy();
        this.createdAt = assignmentModel.getCreatedAt();
        this.updatedAt = assignmentModel.getUpdatedAt();
    }

    public AssignmentModel toAssignmentModel() {
        AssignmentModel assignmentModel = new AssignmentModel(
                this.classroom.toClassroomModel(),
                this.assignmentName,
                this.dueDate,
                this.maxPoints);
        assignmentModel.setId(this.id);
        assignmentModel.setCreatedAt(this.createdAt);
        assignmentModel.setUpdatedAt(this.updatedAt);
        assignmentModel.setCreatedBy(this.createdBy);
        assignmentModel.setUpdatedBy(this.updatedBy);
        return assignmentModel;
    }

}
