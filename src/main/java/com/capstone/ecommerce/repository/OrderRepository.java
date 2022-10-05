package com.capstone.ecommerce.repository;

import com.capstone.ecommerce.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
    public Orders getOrderById(Integer id);
}

