package com.micro.user_service.dto;

import com.micro.user_service.entity.enums.AccountType;

public record UserRegisterDto(String username, String password, AccountType accountType) {
}
