package com.hendi.schoolservice.infrastructure.usertoken.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hendi.schoolservice.entity.usertoken.exception.UserTokenNotFoundException;
import com.hendi.schoolservice.entity.usertoken.model.UserTokenModel;
import com.hendi.schoolservice.infrastructure.config.web.response.WebHttpResponse;
import com.hendi.schoolservice.infrastructure.usertoken.dto.UserTokenPublicData;
import com.hendi.schoolservice.usecase.usertoken.DeleteUserTokenUseCase;
import com.hendi.schoolservice.usecase.usertoken.GetUserTokenUseCase;

@RestController
@RequestMapping("/usertokens")
public class UserTokenController {

    private final GetUserTokenUseCase getUserTokenUseCase;
    private final DeleteUserTokenUseCase deleteUserTokenUseCase;

    public UserTokenController(
            GetUserTokenUseCase getUserTokenUseCase,
            DeleteUserTokenUseCase deleteUserTokenUseCase) {
        this.getUserTokenUseCase = getUserTokenUseCase;
        this.deleteUserTokenUseCase = deleteUserTokenUseCase;
    }

    @GetMapping("/{authToken}")
    public ResponseEntity<WebHttpResponse<UserTokenPublicData>> getUserToken(@PathVariable String authToken) throws UserTokenNotFoundException {
        UserTokenModel userTokenData = getUserTokenUseCase.execute(authToken);
        return new ResponseEntity<>(WebHttpResponse.ok(new UserTokenPublicData(userTokenData)), HttpStatus.OK);
    }

    @DeleteMapping("/{authToken}")
    public ResponseEntity<WebHttpResponse<String>> deleteUserToken(@PathVariable String authToken) {
        deleteUserTokenUseCase.execute(authToken);
        return new ResponseEntity<>(WebHttpResponse.ok("Success"), HttpStatus.OK);
    }

}
