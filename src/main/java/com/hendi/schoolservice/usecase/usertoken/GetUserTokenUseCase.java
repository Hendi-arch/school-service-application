package com.hendi.schoolservice.usecase.usertoken;

import com.hendi.schoolservice.entity.usertoken.exception.UserTokenNotFoundException;
import com.hendi.schoolservice.entity.usertoken.gateway.UserTokenGateway;
import com.hendi.schoolservice.entity.usertoken.model.UserTokenModel;

public class GetUserTokenUseCase {

    private final UserTokenGateway userTokenGateway;

    public GetUserTokenUseCase(UserTokenGateway userTokenGateway) {
        this.userTokenGateway = userTokenGateway;
    }

    public UserTokenModel execute(String authToken) throws UserTokenNotFoundException {
        return userTokenGateway.findUserToken(authToken).orElseThrow(UserTokenNotFoundException::new);
    }

}
