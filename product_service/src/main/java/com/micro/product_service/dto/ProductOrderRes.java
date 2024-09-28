package com.micro.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderRes {
    private Long id;

    private String name;

    private Double price;

    private OrderRes orderRes;
}
