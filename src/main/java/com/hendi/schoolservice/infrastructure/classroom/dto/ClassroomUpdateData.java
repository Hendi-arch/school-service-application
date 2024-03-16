package com.hendi.schoolservice.infrastructure.classroom.dto;

import com.hendi.schoolservice.usecase.classroom.dto.IClassroomUpdateData;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClassroomUpdateData(
                @NotBlank String name,
                @NotNull Long courseId) implements IClassroomUpdateData {
}