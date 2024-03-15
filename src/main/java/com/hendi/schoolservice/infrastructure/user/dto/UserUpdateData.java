package com.hendi.schoolservice.infrastructure.user.dto;

import com.hendi.schoolservice.infrastructure.user.validation.Password;
import com.hendi.schoolservice.usecase.user.dto.IUserUpdateData;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserUpdateData(
        @NotBlank @Password String password,
        @NotNull Long roleId) implements IUserUpdateData {
}
