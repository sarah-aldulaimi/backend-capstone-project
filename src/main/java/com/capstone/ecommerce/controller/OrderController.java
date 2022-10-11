package com.capstone.ecommerce.controller;

import com.capstone.ecommerce.model.Orders;
import com.capstone.ecommerce.model.Product;
import com.capstone.ecommerce.service.OrderService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
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
    private Orders getOrderByID(@PathVariable("orderID") int orderID) {
        return orderService.getOrderById(orderID);
    }

    @DeleteMapping("/{orderID}")
    private void deleteOrder(@PathVariable("orderID") int orderID) {
        orderService.deleteOrderById(orderID);
    }

    @PostMapping
    private Orders saveOrder(@RequestBody Orders orders) {
        return orderService.addOrder(orders);
    }

    @PutMapping("/{orderID}")
    private Orders updateOrder(@PathVariable("orderID") int orderID, @RequestBody Orders orders){
        return orderService.updateOrder(orderID, orders);
    }

    @PostMapping("{orderID}/products")
    private Orders addProductToOrder(@PathVariable("orderID") int orderID, @RequestBody Product product){
        return orderService.addProductToOrder(product.getId(), orderID);
    }

    @DeleteMapping("{orderID}/products/{productID}")
    private void deleteProductToOrder(@PathVariable("orderID") int orderID, @PathVariable("productID") int productID){
        orderService.deleteProductFromOrder(orderID, productID);
    }

    @GetMapping("{orderID}/products")
    private List<Product> viewProductsOfOrder(@PathVariable("orderID") int orderID){
        return orderService.viewProductsOfOrder(orderID);
    }
}