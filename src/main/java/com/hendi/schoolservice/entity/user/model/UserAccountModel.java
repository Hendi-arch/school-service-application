package com.hendi.schoolservice.entity.user.model;

import java.time.LocalDateTime;

import com.hendi.schoolservice.entity.AbstractEntity;
import com.hendi.schoolservice.entity.userrole.model.UserRoleModel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class UserAccountModel extends AbstractEntity<Long> {

    public UserAccountModel(
            String username,
            String password,
            UserRoleModel role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    private String username;

    private String password;

    private UserRoleModel role;

    private String jwtToken;

    private LocalDateTime jwtExpiryDateTime;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
