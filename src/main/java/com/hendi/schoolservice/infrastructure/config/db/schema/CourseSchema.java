package com.hendi.schoolservice.infrastructure.config.db.schema;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.hendi.schoolservice.entity.course.model.CourseModel;

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
@Table(name = "courses")
public class CourseSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_teacher_id"), nullable = false)
    private UserSchema teacher;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 500)
    private String description;

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

    public CourseSchema(CourseModel courseModel) {
        this.id = courseModel.getId();
        this.teacher = new UserSchema(courseModel.getTeacher());
        this.name = courseModel.getName();
        this.description = courseModel.getDescription();
        this.createdBy = courseModel.getCreatedBy();
        this.updatedBy = courseModel.getUpdatedBy();
        this.createdAt = courseModel.getCreatedAt();
        this.updatedAt = courseModel.getUpdatedAt();
    }

    public CourseModel toCourseModel() {
        CourseModel courseModel = new CourseModel(
                this.teacher.toUserAccountModel(),
                this.name,
                this.description);
        courseModel.setId(this.id);
        courseModel.setCreatedAt(this.createdAt);
        courseModel.setUpdatedAt(this.updatedAt);
        courseModel.setCreatedBy(this.createdBy);
        courseModel.setUpdatedBy(this.updatedBy);
        return courseModel;
    }

    public static List<CourseSchema> toCourseSchemaList(List<CourseModel> courseModels) {
        return courseModels.stream().map(CourseSchema::new).toList();
    }

}
