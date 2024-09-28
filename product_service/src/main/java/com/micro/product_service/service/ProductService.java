package com.micro.product_service.service;

import com.micro.product_service.dto.*;
import com.micro.product_service.entity.Product;
import com.micro.product_service.repository.ProductRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    public ProductResponse getProductById(Long id) {
        System.out.println(environment.getProperty("local.server.post")); //TODO
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            UserRes userRes = restTemplate.getForObject("http://USER-SERVICE/user/" + product.get().getUserId(), UserRes.class);
            ProductResponse productResponse = new ProductResponse(
                    product.get().getId(),
                    product.get().getName(),
                    product.get().getPrice(),
                    userRes
            );
            return productResponse;
        } else {
            throw new NotFoundException();
        }
    }

    public List<Product> getProductsByUserId(Long userId) {
        return productRepository.findAllByUserId(userId);
    }

    public Product createProduct(Product product, Long headerId) {
        product.setUserId(headerId);
        return productRepository.save(product);
    }

    public void deleteProductById(Long id, Long headerId) {
        Product product = productRepository.findById(id).orElseThrow();
        if(product.getUserId() != headerId){
            throw new RuntimeException("You don't have access to delete the product");
        }
        productRepository.deleteById(id);
    }

    public ProductOrderRes buyProduct(Long id, Long headerId) {
        Optional<Product> product = productRepository.findById(id);
        System.out.println(product);
        if (product.isPresent()) {
            System.out.println(product);
            OrderReq orderReq = new OrderReq(product.get().getId(), product.get().getUserId(), headerId);
            OrderRes orderRes = restTemplate.postForObject("http://ORDER-SERVICE/order", orderReq, OrderRes.class);
            System.out.println(orderRes);
            ProductOrderRes productOrderRes = new ProductOrderRes(
                    product.get().getId(),
                    product.get().getName(),
                    product.get().getPrice(),
                    orderRes
                    );
            return productOrderRes;
        }
        throw new NotFoundException();
    }
}