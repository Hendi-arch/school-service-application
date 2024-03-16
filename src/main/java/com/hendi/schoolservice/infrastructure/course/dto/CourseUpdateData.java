package com.hendi.schoolservice.infrastructure.course.dto;

import com.hendi.schoolservice.usecase.course.dto.ICourseUpdateData;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseUpdateData(
        @NotBlank String name,
        @NotBlank String description,
        @NotNull Long teacherId) implements ICourseUpdateData {
}
