package com.hendi.schoolservice.infrastructure.course.dto;

import java.time.LocalDateTime;

import com.hendi.schoolservice.entity.course.model.CourseModel;
import com.hendi.schoolservice.entity.user.model.UserAccountModel;
import com.hendi.schoolservice.infrastructure.user.dto.UserPublicData;
import com.hendi.schoolservice.usecase.course.dto.ICoursePublicData;

public record CoursePublicData(
        Long id,
        UserPublicData teacher,
        String name,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) implements ICoursePublicData {

    public CoursePublicData(CourseModel data) {
        this(
                data.getId(),
                mapTeacher(data.getTeacher()),
                data.getName(),
                data.getDescription(),
                data.getCreatedAt(),
                data.getUpdatedAt());
    }

    private static UserPublicData mapTeacher(UserAccountModel data) {
        return new UserPublicData(data);
    }
}
