package com.hendi.schoolservice.infrastructure.user.dto;

import com.hendi.schoolservice.infrastructure.user.validation.Password;
import com.hendi.schoolservice.infrastructure.user.validation.Username;
import com.hendi.schoolservice.usecase.user.dto.IUserCreateData;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserCreateData(
        @NotBlank @Username String username,
        @NotBlank @Password String password,
        @NotNull Long roleId) implements IUserCreateData {
}
