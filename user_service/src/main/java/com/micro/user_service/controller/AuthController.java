package com.micro.user_service.controller;

import com.micro.user_service.dto.LoginResponse;
import com.micro.user_service.dto.UserLoginDto;
import com.micro.user_service.dto.UserRegisterDto;
import com.micro.user_service.entity.User;
import com.micro.user_service.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("user/api/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {
    //s
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody UserRegisterDto user
    ) throws IOException {
        try {
            String response = authService.register(user);
            return ResponseEntity.ok()
                    .body("User registered successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody UserLoginDto request
    ) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok()
                .body(response);
    }

}
