package com.micro.order_service.repository;


import com.micro.order_service.entity.OrderProduct;
import com.micro.order_service.entity.enums.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderProduct,Long> {
    List<OrderProduct> findByOrderType(OrderType orderType);
}
