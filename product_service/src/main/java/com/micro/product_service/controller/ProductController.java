package com.micro.product_service.controller;

import com.micro.product_service.dto.ProductOrderRes;
import com.micro.product_service.dto.ProductResponse;
import com.micro.product_service.entity.Product;
import com.micro.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        ProductResponse productResponse = productService.getProductById(id);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping("vendor/{userId}")
    public ResponseEntity<List<Product>> getProductsByUserId(@PathVariable Long userId) {
        List<Product> productList = productService.getProductsByUserId(userId);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product, @RequestHeader("userJwtId") Long headerId) {
        Product newProduct = productService.createProduct(product,headerId);
        return new ResponseEntity<>(newProduct, HttpStatus.OK);
        // Logic to create product
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id,@RequestHeader("userJwtId") Long headerId) {
        productService.deleteProductById(id,headerId);
        return new ResponseEntity<>(" deleted successfully", HttpStatus.OK);
        // Logic to get product by ID
    }

    @GetMapping("/buy/{id}")
    public ResponseEntity<ProductOrderRes> buyProduct(@PathVariable Long id, @RequestHeader("userJwtId") Long headerId) {
        ProductOrderRes newProduct = productService.buyProduct(id,headerId);
        return new ResponseEntity<>(newProduct, HttpStatus.OK);
        // Logic to create product
    }
}
