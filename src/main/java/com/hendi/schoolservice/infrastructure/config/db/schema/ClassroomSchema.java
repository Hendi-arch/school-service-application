package com.hendi.schoolservice.infrastructure.config.db.schema;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.hendi.schoolservice.entity.classroom.model.ClassroomModel;

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
@Table(name = "classrooms")
public class ClassroomSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_course_id"), nullable = false)
    private CourseSchema course;

    @Column(nullable = false)
    private String name;

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

    public ClassroomSchema(ClassroomModel classroomModel) {
        this.id = classroomModel.getId();
        this.course = new CourseSchema(classroomModel.getCourse());
        this.name = classroomModel.getName();
        this.createdBy = classroomModel.getCreatedBy();
        this.updatedBy = classroomModel.getUpdatedBy();
        this.createdAt = classroomModel.getCreatedAt();
        this.updatedAt = classroomModel.getUpdatedAt();
    }

    public ClassroomModel toClassroomModel() {
        ClassroomModel courseModel = new ClassroomModel(
                this.course.toCourseModel(),
                this.name);
        courseModel.setId(this.id);
        courseModel.setCreatedAt(this.createdAt);
        courseModel.setUpdatedAt(this.updatedAt);
        courseModel.setCreatedBy(this.createdBy);
        courseModel.setUpdatedBy(this.updatedBy);
        return courseModel;
    }

}
