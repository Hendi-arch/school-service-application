package com.hendi.schoolservice.usecase.user;

import com.hendi.schoolservice.entity.user.exception.UserNotFoundException;
import com.hendi.schoolservice.entity.user.gateway.UserGateway;
import com.hendi.schoolservice.entity.user.model.UserAccountModel;

public class GetUserUseCase {

    private final UserGateway userGateway;

    public GetUserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public UserAccountModel findById(Long id) throws UserNotFoundException {
        return userGateway
                .findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public UserAccountModel findByUsername(String username) throws UserNotFoundException {
        return userGateway
                .findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }

}
