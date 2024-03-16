package com.hendi.schoolservice.infrastructure.userrole.dto;

import java.time.LocalDateTime;

import com.hendi.schoolservice.entity.userrole.model.UserRoleModel;
import com.hendi.schoolservice.usecase.userrole.dto.IUserRolePublicData;

public record UserRolePublicData(
        Long id,
        String role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) implements IUserRolePublicData {

    public UserRolePublicData(UserRoleModel data) {
        this(
                data.getId(),
                data.getRole().toString(),
                data.getCreatedAt(),
                data.getUpdatedAt());
    }

}
