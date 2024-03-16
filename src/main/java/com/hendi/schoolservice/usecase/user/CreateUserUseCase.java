package com.hendi.schoolservice.usecase.user;

import com.hendi.schoolservice.entity.user.gateway.UserGateway;
import com.hendi.schoolservice.entity.user.model.UserAccountModel;
import com.hendi.schoolservice.entity.userrole.exception.UserRoleNotFoundException;
import com.hendi.schoolservice.entity.userrole.gateway.UserRoleGateway;
import com.hendi.schoolservice.entity.userrole.model.UserRoleModel;
import com.hendi.schoolservice.usecase.user.dto.IUserCreateData;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CreateUserUseCase {

    private final UserGateway userGateway;
    private final UserRoleGateway userRoleGateway;
    private final PasswordEncoder passwordEncoder;

    public CreateUserUseCase(
            UserGateway userGateway,
            UserRoleGateway userRoleGateway,
            PasswordEncoder passwordEncoder) {
        this.userGateway = userGateway;
        this.userRoleGateway = userRoleGateway;
        this.passwordEncoder = passwordEncoder;
    }

    public UserAccountModel execute(IUserCreateData data) throws UserRoleNotFoundException {
        String username = data.username();
        String password = passwordEncoder.encode(data.password());
        Long roleId = data.roleId();

        UserRoleModel userRole = userRoleGateway.findById(roleId).orElseThrow(UserRoleNotFoundException::new);
        UserAccountModel userAccount = new UserAccountModel(username, password, userRole);
        return userGateway.create(userAccount);
    }

}
