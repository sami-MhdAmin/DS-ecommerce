package com.micro.product_service.dto;

public record OrderReq(Long productId,Long vendorId, Long buyerId) {
}
