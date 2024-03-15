package com.hendi.schoolservice.infrastructure.config.db.schema;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.hendi.schoolservice.entity.grade.model.GradeModel;

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
@Table(name = "grades")
public class GradeSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_student_id"), nullable = false)
    private UserSchema student;

    @ManyToOne
    @JoinColumn(name = "classroom_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_classroom_id"), nullable = false)
    private ClassroomSchema classroom;

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

    public GradeSchema(GradeModel gradeModel) {
        this.id = gradeModel.getId();
        this.student = new UserSchema(gradeModel.getStudent());
        this.classroom = new ClassroomSchema(gradeModel.getClassroom());
        this.grade = gradeModel.getGrade();
        this.createdBy = gradeModel.getCreatedBy();
        this.updatedBy = gradeModel.getUpdatedBy();
        this.createdAt = gradeModel.getCreatedAt();
        this.updatedAt = gradeModel.getUpdatedAt();
    }

    public GradeModel toGradeModel() {
        GradeModel gradeModel = new GradeModel(
                this.student.toUserAccountModel(),
                this.classroom.toClassroomModel(),
                this.grade);
        gradeModel.setId(this.id);
        gradeModel.setCreatedAt(this.createdAt);
        gradeModel.setUpdatedAt(this.updatedAt);
        gradeModel.setCreatedBy(this.createdBy);
        gradeModel.setUpdatedBy(this.updatedBy);
        return gradeModel;
    }

}
