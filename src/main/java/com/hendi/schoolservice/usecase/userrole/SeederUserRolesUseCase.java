package com.hendi.schoolservice.usecase.userrole;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.hendi.schoolservice.entity.userrole.gateway.UserRoleGateway;
import com.hendi.schoolservice.entity.userrole.model.UserRoleModel;
import com.hendi.schoolservice.infrastructure.config.db.schema.UserRoleSchema.RoleEnum;

public class SeederUserRolesUseCase {

    private final UserRoleGateway userRoleGateway;

    public SeederUserRolesUseCase(UserRoleGateway userRoleGateway) {
        this.userRoleGateway = userRoleGateway;
    }

    public List<UserRoleModel> execute() {
        List<UserRoleModel> existingRoles = userRoleGateway.findAll();
        if (!existingRoles.isEmpty()) {
            return existingRoles;
        }

        List<UserRoleModel> userRolesToSeed = Stream
                .of(RoleEnum.SUPER_ADMIN, RoleEnum.ADMIN, RoleEnum.USER, RoleEnum.TEACHER, RoleEnum.STUDENT)
                .map(UserRoleModel::new)
                .collect(Collectors.toList());
        return userRoleGateway.createAll(userRolesToSeed);
    }

}
