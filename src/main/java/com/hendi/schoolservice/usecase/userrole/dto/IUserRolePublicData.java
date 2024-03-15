package com.hendi.schoolservice.usecase.userrole.dto;

import java.time.LocalDateTime;

public interface IUserRolePublicData {

    Long id();

    String role();

    LocalDateTime createdAt();

    LocalDateTime updatedAt();

}
