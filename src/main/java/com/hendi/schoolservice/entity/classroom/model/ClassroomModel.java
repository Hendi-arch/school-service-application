package com.hendi.schoolservice.entity.classroom.model;

import java.time.LocalDateTime;

import com.hendi.schoolservice.entity.AbstractEntity;
import com.hendi.schoolservice.entity.course.model.CourseModel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class ClassroomModel extends AbstractEntity<Long> {

    public ClassroomModel(
            CourseModel course,
            String name) {
        this.course = course;
        this.name = name;
    }

    private CourseModel course;

    private String name;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
