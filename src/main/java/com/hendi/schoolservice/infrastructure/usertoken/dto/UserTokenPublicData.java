package com.hendi.schoolservice.infrastructure.usertoken.dto;

import java.time.LocalDateTime;

import com.hendi.schoolservice.entity.usertoken.model.UserTokenModel;
import com.hendi.schoolservice.usecase.usertoken.dto.IUserTokenPublicData;

import jakarta.validation.constraints.NotBlank;

public record UserTokenPublicData(
        @NotBlank String token,
        LocalDateTime expiryDateTime) implements IUserTokenPublicData {

    public UserTokenPublicData(UserTokenModel data) {
        this(data.getToken(), data.getExpiryDateTime());
    }
}
