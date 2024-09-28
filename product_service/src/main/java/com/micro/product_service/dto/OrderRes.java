package com.micro.product_service.dto;

public record OrderRes(Long id, String orderType, UserRes vendor, UserRes buyer) {
}
