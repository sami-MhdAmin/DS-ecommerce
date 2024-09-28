package com.micro.user_service.dto;

public record LoginResponse(String username, String token, String accountType) {
}
