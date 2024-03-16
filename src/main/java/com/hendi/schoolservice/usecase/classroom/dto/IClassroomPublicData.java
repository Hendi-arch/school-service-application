package com.hendi.schoolservice.usecase.classroom.dto;

import java.time.LocalDateTime;

import com.hendi.schoolservice.usecase.course.dto.ICoursePublicData;

public interface IClassroomPublicData {

    Long id();

    ICoursePublicData course();

    String name();

    LocalDateTime createdAt();

    LocalDateTime updatedAt();

}
