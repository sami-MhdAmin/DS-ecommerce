package com.micro.order_service.service;

import com.micro.order_service.dto.UserRes;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = "userService", fallbackMethod = "fallbackGetUser")
    @Retry(name = "userService")
    public UserRes getUser(Long userId) {
        return restTemplate.getForObject("http://USER-SERVICE/user/" + userId, UserRes.class);
    }

    public UserRes fallbackGetUser(Long userId, Throwable t) {
        UserRes userResFall = new UserRes(0L,"DS-User","XX");
        return userResFall;
    }
}
