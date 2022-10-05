package com.capstone.ecommerce.controller;

import com.capstone.ecommerce.model.Orders;
import com.capstone.ecommerce.model.Product;
import com.capstone.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping
    private List<Orders> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderID}")
    private Orders getUserByID(@PathVariable("orderID") int orderID) {
        return orderService.getOrderById(orderID);
    }

    @DeleteMapping("/{orderID}")
    private void deleteOrder(@PathVariable("orderID") int orderID) {
        orderService.getOrderById(orderID);
    }

    @PostMapping
    private void saveOrder(@RequestBody Orders orders) {
        orderService.addOrder(orders);
    }

    @PutMapping("/{orderID}")
    private void updateOrder(@PathVariable("orderID") int orderID, @RequestBody Orders orders){
        orderService.updateOrder(orderID, orders);
    }

    @PostMapping("{orderID}/products")
    private void addProductToOrder(@PathVariable("orderID") int orderID, @RequestBody Product product){
        orderService.addProductToOrder(product.getId(), orderID);
    }
}
