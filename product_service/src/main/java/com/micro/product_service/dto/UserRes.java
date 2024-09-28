package com.micro.product_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRes {
    private Long id;
    private String username;
    private String accountType;
}
