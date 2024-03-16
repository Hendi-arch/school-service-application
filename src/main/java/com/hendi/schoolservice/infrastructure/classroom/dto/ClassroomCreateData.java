package com.hendi.schoolservice.infrastructure.classroom.dto;

import com.hendi.schoolservice.usecase.classroom.dto.IClassroomCreateData;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClassroomCreateData(
        @NotBlank String name,
        @NotNull Long courseId) implements IClassroomCreateData {
}
