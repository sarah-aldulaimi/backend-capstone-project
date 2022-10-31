package com.capstone.ecommerce.controller;
import com.capstone.ecommerce.model.Order;
import com.capstone.ecommerce.model.Product;
import com.capstone.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping
    private List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }
    @GetMapping("/{orderID}")
    private Order getOrderByID(@PathVariable("orderID") int orderID) {
        Order order = orderService.getOrderById(orderID);
        return order;
    }
    @DeleteMapping("/{orderID}")
    private ResponseEntity deleteOrder(@PathVariable("orderID") int orderID) {
        orderService.deleteOrderById(orderID);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping
    private ResponseEntity saveOrder(@RequestBody Order order) {
        int id = orderService.addOrder(order);
        return new ResponseEntity(id, HttpStatus.CREATED);
    }
    @PutMapping("/{orderID}")
    private ResponseEntity updateOrder(@PathVariable("orderID") int orderID, @RequestBody Order order){
        int id = orderService.updateOrder(orderID, order);
        return new ResponseEntity(id, HttpStatus.NO_CONTENT);
    }
    @PostMapping("{orderID}/products/{productCount}")
    private ResponseEntity addProductToOrder(@PathVariable("orderID") int orderID,@PathVariable("productCount") int productCount, @RequestBody Product product){
        orderService.addProductToOrder(product.getId(), orderID, productCount);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @DeleteMapping("{orderID}/products/{productID}/{productCount}")
    private ResponseEntity deleteProductFromOrder(@PathVariable("orderID") int orderID, @PathVariable("productID") int productID, @PathVariable("productCount") int productCount){
        orderService.deleteProductFromOrder(orderID, productID, productCount);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("{orderID}/products")
    private List<Product> viewProductsOfOrder(@PathVariable("orderID") int orderID){
        return orderService.viewProductsOfOrder(orderID);
    }
    @GetMapping("/users/{userID}")
    private List<Order> getAllOrdersForUser(@PathVariable("userID") int userID){
        return orderService.viewAllOrdersByUser(userID);
    }
}