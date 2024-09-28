package com.micro.user_service.service;

import com.micro.user_service.dto.LoginResponse;
import com.micro.user_service.dto.UserLoginDto;
import com.micro.user_service.dto.UserRegisterDto;
import com.micro.user_service.entity.User;
import com.micro.user_service.repository.UserRepository;
import com.micro.user_service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String register(UserRegisterDto user) throws IOException {
        if (userAlreadyExists(user.username())) {
            throw new RuntimeException("User already exists");
        }
        User newuser = new User();
        newuser.setAccountType(user.accountType());
        newuser.setPassword(passwordEncoder.encode(user.password()));
        newuser.setUsername(user.username());

        userRepository.save(newuser);

        return "bla bla bla";
    }

    private boolean userAlreadyExists(String username) {
        return userRepository.existsByUsername(username);
    }


    public LoginResponse login(UserLoginDto request) throws BadCredentialsException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

            Optional<User> temp = userRepository.findByUsername(request.username());
            System.out.println(temp);
            String jwtToken = jwtService.generateToken(temp.orElseThrow());

            LoginResponse loginResponse = new LoginResponse(temp.get().getUsername(), jwtToken, temp.get().getAccountType().toString());
            return loginResponse;

        } catch (BadCredentialsException e) {
            throw new RuntimeException("User not found");
        }
    }
}

