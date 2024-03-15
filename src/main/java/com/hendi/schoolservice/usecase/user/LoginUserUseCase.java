package com.hendi.schoolservice.usecase.user;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hendi.schoolservice.entity.user.exception.PasswordNotMatchException;
import com.hendi.schoolservice.entity.user.exception.UserNotFoundException;
import com.hendi.schoolservice.entity.user.gateway.UserGateway;
import com.hendi.schoolservice.entity.user.model.UserAccountModel;
import com.hendi.schoolservice.entity.usertoken.gateway.UserTokenGateway;
import com.hendi.schoolservice.entity.usertoken.model.UserTokenModel;
import com.hendi.schoolservice.infrastructure.config.web.security.service.MyUserDetailService;
import com.hendi.schoolservice.infrastructure.config.web.security.util.JwtUtils;
import com.hendi.schoolservice.usecase.user.dto.IUserLoginData;

public class LoginUserUseCase {

    private final UserGateway userGateway;
    private final UserTokenGateway userTokenGateway;
    private final JwtUtils jwtUtils;
    private final MyUserDetailService myUserDetailService;
    private final PasswordEncoder passwordEncoder;

    public LoginUserUseCase(
            UserGateway userGateway,
            UserTokenGateway userTokenGateway,
            JwtUtils jwtUtils,
            MyUserDetailService myUserDetailService,
            PasswordEncoder passwordEncoder) {
        this.userGateway = userGateway;
        this.userTokenGateway = userTokenGateway;
        this.jwtUtils = jwtUtils;
        this.myUserDetailService = myUserDetailService;
        this.passwordEncoder = passwordEncoder;
    }

    public UserAccountModel execute(IUserLoginData data) throws UserNotFoundException, PasswordNotMatchException {
        String username = data.username();
        String password = data.password();

        UserAccountModel userAccountData = userGateway.findByUsername(username).orElseThrow(UserNotFoundException::new);

        boolean isPasswordMatch = passwordEncoder.matches(password, userAccountData.getPassword());
        if (!isPasswordMatch) {
            throw new PasswordNotMatchException();
        }

        UserDetails userDetails = myUserDetailService.loadUserByUsername(username);
        String jwtToken = jwtUtils.generateJwtToken(userDetails);
        LocalDateTime jwtExpiryDateTime = jwtUtils.getExpirationFromJwtToken(jwtToken).toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDateTime();

        userAccountData.setJwtToken(jwtToken);
        userAccountData.setJwtExpiryDateTime(jwtExpiryDateTime);

        UserTokenModel userTokenData = new UserTokenModel(userAccountData, jwtToken, jwtExpiryDateTime);
        userTokenGateway.create(userTokenData);
        return userAccountData;
    }

}
