package com.hendi.schoolservice.entity.course.model;

import java.time.LocalDateTime;

import com.hendi.schoolservice.entity.AbstractEntity;
import com.hendi.schoolservice.entity.user.model.UserAccountModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class CourseModel extends AbstractEntity<Long> {

    public CourseModel(
            UserAccountModel teacher,
            String name,
            String description) {
        this.teacher = teacher;
        this.name = name;
        this.description = description;
    }

    private UserAccountModel teacher;

    private String name;

    private String description;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
