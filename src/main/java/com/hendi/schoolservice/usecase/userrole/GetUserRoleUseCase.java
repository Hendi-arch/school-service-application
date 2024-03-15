package com.hendi.schoolservice.usecase.userrole;

import com.hendi.schoolservice.entity.userrole.exception.UserRoleNotFoundException;
import com.hendi.schoolservice.entity.userrole.gateway.UserRoleGateway;
import com.hendi.schoolservice.entity.userrole.model.UserRoleModel;

public class GetUserRoleUseCase {

    private final UserRoleGateway userRoleGateway;

    public GetUserRoleUseCase(UserRoleGateway userRoleGateway) {
        this.userRoleGateway = userRoleGateway;
    }

    public UserRoleModel execute(Long id) throws UserRoleNotFoundException {
        return userRoleGateway
                .findById(id)
                .orElseThrow(UserRoleNotFoundException::new);
    }

}
