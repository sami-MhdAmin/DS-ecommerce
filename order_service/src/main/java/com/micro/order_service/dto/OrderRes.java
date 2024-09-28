package com.micro.order_service.dto;

public record OrderRes(Long id, String orderType, UserRes vendor, UserRes buyer) {
}