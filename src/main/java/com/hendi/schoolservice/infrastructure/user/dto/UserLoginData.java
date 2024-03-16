package com.hendi.schoolservice.infrastructure.user.dto;

import com.hendi.schoolservice.infrastructure.user.validation.Password;
import com.hendi.schoolservice.infrastructure.user.validation.Username;
import com.hendi.schoolservice.usecase.user.dto.IUserLoginData;

import jakarta.validation.constraints.NotBlank;

public record UserLoginData(
        @NotBlank @Username String username,
        @NotBlank @Password String password) implements IUserLoginData {
}
