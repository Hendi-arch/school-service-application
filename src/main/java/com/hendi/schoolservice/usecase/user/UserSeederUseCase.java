package com.hendi.schoolservice.usecase.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.hendi.schoolservice.entity.user.gateway.UserGateway;
import com.hendi.schoolservice.entity.user.model.UserAccountModel;
import com.hendi.schoolservice.entity.userrole.gateway.UserRoleGateway;
import com.hendi.schoolservice.entity.userrole.model.UserRoleModel;

public class UserSeederUseCase {

    private final UserGateway userGateway;
    private final UserRoleGateway userRoleGateway;
    private final PasswordEncoder passwordEncoder;

    public UserSeederUseCase(
            UserGateway userGateway,
            UserRoleGateway userRoleGateway,
            PasswordEncoder passwordEncoder) {
        this.userGateway = userGateway;
        this.userRoleGateway = userRoleGateway;
        this.passwordEncoder = passwordEncoder;
    }

    public void execute() {
        if (!userGateway.findAll().isEmpty()) {
            return;
        }

        List<UserRoleModel> existingRoles = userRoleGateway.findAll();
        List<UserAccountModel> usersToSeed = existingRoles.stream()
                .map(this::createUserAccount)
                .collect(Collectors.toList());
        userGateway.createAll(usersToSeed);
    }

    private UserAccountModel createUserAccount(UserRoleModel role) {
        return new UserAccountModel(
                role.getRole().name().toLowerCase(),
                passwordEncoder.encode("SecurePassword123$"),
                role);
    }

}
