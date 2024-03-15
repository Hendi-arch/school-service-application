package com.hendi.schoolservice.infrastructure.usertoken.gateway;

import java.util.Optional;

import com.hendi.schoolservice.entity.usertoken.gateway.UserTokenGateway;
import com.hendi.schoolservice.entity.usertoken.model.UserTokenModel;
import com.hendi.schoolservice.infrastructure.config.db.repository.UserTokenRepository;
import com.hendi.schoolservice.infrastructure.config.db.schema.UserTokenSchema;

public class UserTokenDatabaseGateway implements UserTokenGateway {

    private final UserTokenRepository repository;

    public UserTokenDatabaseGateway(UserTokenRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserTokenModel create(UserTokenModel userTokenModel) {
        return repository.save(new UserTokenSchema(userTokenModel)).toUserTokenModel();
    }

    @Override
    public void delete(String authToken) {
        repository.deleteByToken(authToken);
    }

    @Override
    public Optional<UserTokenModel> findUserToken(String authToken) {
        return repository.findByToken(authToken).map(UserTokenSchema::toUserTokenModel);
    }

}
