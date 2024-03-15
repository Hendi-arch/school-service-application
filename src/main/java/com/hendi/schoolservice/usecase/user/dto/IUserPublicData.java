package com.hendi.schoolservice.usecase.user.dto;

import java.time.LocalDateTime;

import com.hendi.schoolservice.usecase.userrole.dto.IUserRolePublicData;

public interface IUserPublicData {

    Long id();

    String username();

    IUserRolePublicData role();

    LocalDateTime createdAt();

    LocalDateTime updatedAt();

}
