package com.micro.order_service.controller;

import com.micro.order_service.dto.OrderProductRes;
import com.micro.order_service.dto.OrderRes;
import com.micro.order_service.entity.OrderProduct;
import com.micro.order_service.entity.enums.OrderType;
import com.micro.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping()
    public ResponseEntity<List<OrderProduct>> getOrders() {
        List<OrderProduct> orderList = orderService.getOrders();
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderProductRes> getOrderById(@PathVariable Long id) {
        OrderProductRes order = orderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/type/{orderType}")
    public ResponseEntity<List<OrderProduct>> getOrderByOrderType(@PathVariable OrderType orderType) {
        List<OrderProduct> orderList = orderService.getOrderByOrderType(orderType);
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderRes> createOrder(@RequestBody OrderProduct order) {
        System.out.println("In Post Mapping");
        OrderRes newOrder = orderService.createOrder(order);
        return new ResponseEntity<>(newOrder, HttpStatus.OK);
    }
}
