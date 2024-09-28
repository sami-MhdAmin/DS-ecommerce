package com.micro.order_service.dto;

public record OrderProductRes(Long id, String orderType, ProductRes productRes ,UserRes vendor, UserRes buyer) {
}
