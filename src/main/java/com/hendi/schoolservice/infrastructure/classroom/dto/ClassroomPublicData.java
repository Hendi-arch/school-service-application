package com.hendi.schoolservice.infrastructure.classroom.dto;

import java.time.LocalDateTime;

import com.hendi.schoolservice.entity.classroom.model.ClassroomModel;
import com.hendi.schoolservice.entity.course.model.CourseModel;
import com.hendi.schoolservice.infrastructure.course.dto.CoursePublicData;
import com.hendi.schoolservice.usecase.classroom.dto.IClassroomPublicData;

public record ClassroomPublicData(
        Long id,
        CoursePublicData course,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) implements IClassroomPublicData {

    public ClassroomPublicData(ClassroomModel data) {
        this(
                data.getId(),
                mapCourse(data.getCourse()),
                data.getName(),
                data.getCreatedAt(),
                data.getUpdatedAt());
    }

    private static CoursePublicData mapCourse(CourseModel data) {
        return new CoursePublicData(data);
    }
}
