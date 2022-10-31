package com.capstone.ecommerce.repository;

import com.capstone.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    public Order getOrderById(Integer id);
}

