package com.micro.order_service.service;


import com.micro.order_service.dto.OrderProductRes;
import com.micro.order_service.dto.OrderRes;
import com.micro.order_service.dto.ProductRes;
import com.micro.order_service.dto.UserRes;
import com.micro.order_service.entity.OrderProduct;
import com.micro.order_service.entity.enums.OrderType;
import com.micro.order_service.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private RestTemplate restTemplate;

    public List<OrderProduct> getOrders() {
        return orderRepository.findAll();
    }

//    public OrderProduct getOrderById(Long id) {
//        return orderRepository.findById(id).orElseThrow();
//    }

    public OrderProductRes getOrderById(Long id)
    {
         OrderProduct order = orderRepository.findById(id).orElseThrow();
        UserRes vendor = userService.getUser(order.getVendorId());
        UserRes buyer = userService.getUser(order.getBuyerId());
        ProductRes productRes = productService.getProduct(order.getProductId());
        OrderProductRes orderProductRes = new OrderProductRes(order.getId(), order.getOrderType().toString(),
                productRes,vendor,buyer);

        return orderProductRes;
    }

    public OrderRes createOrder(OrderProduct order) {
        UserRes vendor = userService.getUser(order.getVendorId());  //restTemplate.getForObject("http://USER-SERVICE/user/" + order.getVendorId(), UserRes.class);
        UserRes buyer = userService.getUser(order.getBuyerId());       //restTemplate.getForObject("http://USER-SERVICE/user/" + order.getBuyerId(), UserRes.class);
//        System.out.println(vendor);
//        System.out.println(buyer);
        String vendorAccountType = String.valueOf(vendor.accountType().charAt(0));
        String buyerAccountType = String.valueOf(buyer.accountType().charAt(0));
        order.setOrderType(OrderType.valueOf(vendorAccountType + "2" + buyerAccountType));
        OrderProduct tempOrder = orderRepository.save(order);
        OrderRes orderRes = new OrderRes(tempOrder.getId(), tempOrder.getOrderType().toString(), vendor, buyer);
        return orderRes;
    }

    public List<OrderProduct> getOrderByOrderType(OrderType orderType) {
        return orderRepository.findByOrderType(orderType);
    }
}
