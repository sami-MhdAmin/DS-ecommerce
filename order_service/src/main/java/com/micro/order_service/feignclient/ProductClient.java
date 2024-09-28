package com.micro.order_service.feignclient;

import com.micro.order_service.dto.ProductRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//Not Used
@FeignClient(name = "PRODUCT-SERVICE", path = "/PRODUCT-SERVICE")
//@RibbonClient(name = "address-service")
public interface ProductClient {

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductRes> getProductById(@PathVariable("id") Long id);

}