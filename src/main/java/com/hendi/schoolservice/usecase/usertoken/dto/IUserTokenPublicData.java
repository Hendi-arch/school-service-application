package com.hendi.schoolservice.usecase.usertoken.dto;

import java.time.LocalDateTime;

public interface IUserTokenPublicData {
    
    String token();

    LocalDateTime expiryDateTime();

}
