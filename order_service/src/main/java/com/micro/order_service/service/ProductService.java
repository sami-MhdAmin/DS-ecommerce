package com.micro.order_service.service;

import com.micro.order_service.dto.ProductRes;
import com.micro.order_service.dto.UserRes;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {
    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = "productService", fallbackMethod = "fallbackGetProduct")
    @Retry(name = "productService")
    public ProductRes getProduct(Long productId) {
        return restTemplate.getForObject("http://PRODUCT-SERVICE/product/" + productId, ProductRes.class);
    }

    public ProductRes fallbackGetProduct(Long productId, Throwable t) {
        ProductRes productResFall = new ProductRes(0L,"DS-Product",00D);
        return productResFall;
    }
}
