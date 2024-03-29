package com.hendi.schoolservice.infrastructure.user.gateway;

import java.util.List;
import java.util.Optional;

import com.hendi.schoolservice.entity.user.gateway.UserGateway;
import com.hendi.schoolservice.entity.user.model.UserAccountModel;
import com.hendi.schoolservice.infrastructure.config.db.repository.UserRepository;
import com.hendi.schoolservice.infrastructure.config.db.schema.UserSchema;

public class UserDatabaseGateway implements UserGateway {

    private final UserRepository repository;

    public UserDatabaseGateway(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserAccountModel create(UserAccountModel userAccountModel) {
        return repository.save(new UserSchema(userAccountModel)).toUserAccountModel();
    }

    @Override
    public UserAccountModel update(UserAccountModel userAccountModel) {
        return repository.save(new UserSchema(userAccountModel)).toUserAccountModel();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<UserAccountModel> findById(Long id) {
        return repository.findById(id).map(UserSchema::toUserAccountModel);
    }

    @Override
    public Optional<UserAccountModel> findByUsername(String username) {
        return repository.findByUsername(username).map(UserSchema::toUserAccountModel);
    }

    @Override
    public List<UserAccountModel> findAll() {
        return repository.findAll().stream().map(UserSchema::toUserAccountModel).toList();
    }

    @Override
    public List<UserAccountModel> createAll(List<UserAccountModel> userAccountModels) {
        return repository.saveAll(UserSchema.toUserSchemaList(userAccountModels)).stream()
                .map(UserSchema::toUserAccountModel).toList();
    }

}
