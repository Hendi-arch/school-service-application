package com.hendi.schoolservice.entity.user.gateway;

import java.util.Optional;
import java.util.List;

import com.hendi.schoolservice.entity.user.exception.UserNotFoundException;
import com.hendi.schoolservice.entity.user.model.UserAccountModel;

public interface UserGateway {

    UserAccountModel create(UserAccountModel userAccountModel);

    UserAccountModel update(UserAccountModel userAccountModel);

    List<UserAccountModel> createAll(List<UserAccountModel> userAccountModels);

    void delete(Long id) throws UserNotFoundException;

    Optional<UserAccountModel> findById(Long id);

    Optional<UserAccountModel> findByUsername(String username);

    List<UserAccountModel> findAll();

}
