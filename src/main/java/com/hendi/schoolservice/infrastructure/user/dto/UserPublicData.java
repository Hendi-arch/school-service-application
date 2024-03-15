package com.hendi.schoolservice.infrastructure.user.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.hendi.schoolservice.entity.user.model.UserAccountModel;
import com.hendi.schoolservice.entity.userrole.model.UserRoleModel;
import com.hendi.schoolservice.infrastructure.userrole.dto.UserRolePublicData;
import com.hendi.schoolservice.usecase.user.dto.IUserPublicData;

@JsonInclude(value = Include.NON_NULL)
public record UserPublicData(
        Long id,
        String username,
        UserRolePublicData role,
        String jwtToken,
        LocalDateTime jwtExpiryDateTime,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) implements IUserPublicData {

    public UserPublicData(UserAccountModel data) {
        this(
                data.getId(),
                data.getUsername(),
                mapRole(data.getRole()),
                data.getJwtToken(),
                data.getJwtExpiryDateTime(),
                data.getCreatedAt(),
                data.getUpdatedAt());
    }

    private static UserRolePublicData mapRole(UserRoleModel data) {
        return new UserRolePublicData(data);
    }
}
