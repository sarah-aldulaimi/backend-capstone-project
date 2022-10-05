package com.capstone.ecommerce.service;

import com.capstone.ecommerce.model.Orders;
import com.capstone.ecommerce.model.Product;
import com.capstone.ecommerce.repository.OrderRepository;
import com.capstone.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;

    public List<Orders> getAllOrders(){
        return orderRepository.findAll();
    }

    public Orders getOrderById(int id) {
        return orderRepository.getOrderById(id);
    }

    public void updateOrder(int id, Orders orders) {
        if (orderRepository.existsById(id)) {
            orders.setUserID(id);
            orderRepository.save(orders);
        }
    }
    public void deleteOrderById (int id){
        orderRepository.deleteById(id);
    }

    public void addOrder(Orders orders){
        int orderNumber = gen();
        orders.setOrderNumber(orderNumber);
        orderRepository.save(orders);
    }

    public int gen() {
        Random r = new Random( System.currentTimeMillis() );
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }

    public void addProductToOrder(int productID, int orderID){
        Orders order = orderRepository.getOrderById(orderID);
        order.setProductCount(order.getProductCount()+1);
        Product product = productRepository.getProductById(productID);
        float totalPrice = order.getTotalCost() + product.getPrice();
        order.setTotalCost(totalPrice);
        order.getProducts().add(product);
        orderRepository.save(order);
    }
}