package com.hendi.schoolservice.usecase.course.dto;

import java.time.LocalDateTime;

import com.hendi.schoolservice.usecase.user.dto.IUserPublicData;

public interface ICoursePublicData {

    Long id();

    IUserPublicData teacher();

    String name();

    String description();

    LocalDateTime createdAt();

    LocalDateTime updatedAt();

}
