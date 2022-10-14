package com.capstone.ecommerce.service;

import com.capstone.ecommerce.model.Orders;
import com.capstone.ecommerce.model.Product;
import com.capstone.ecommerce.repository.OrderRepository;
import com.capstone.ecommerce.repository.ProductRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Orders updateOrder(int id, Orders newOrder) {
        if (orderRepository.existsById(id)) {
            Orders oldOrder = orderRepository.getOrderById(id);
            newOrder.setProducts(oldOrder.getProducts());
            orderRepository.save(newOrder);
        }
        return newOrder;
    }
    public void deleteOrderById (int id){
        orderRepository.deleteById(id);
    }

    public Orders addOrder(Orders orders){
        int orderNumber = gen();
        orders.setOrderNumber(orderNumber);
        orderRepository.save(orders);
        return orders;
    }

    public int gen() {
        Random r = new Random( System.currentTimeMillis() );
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }

    public List<Product> addProductToOrder(int productID, int orderID, int productCount){
        Orders order = orderRepository.getOrderById(orderID);
//        order.setProductCount(order.getProductCount()+1);
        Product product = productRepository.getProductById(productID);
        float totalPrice = order.getTotalCost() + product.getPrice();
        order.setTotalCost(totalPrice);
        order.getProducts().add(product);
        int newProductCount = order.getProductCount() + productCount;
        order.setProductCount(newProductCount);
        orderRepository.save(order);
        return order.getProducts();
    }

    public void deleteProductFromOrder(int orderID, int productID){
        Orders order = orderRepository.getOrderById(orderID);
        Product product = productRepository.getProductById(productID);
//        order.setProductCount(order.getProductCount()-1);
        float totalPrice = order.getTotalCost() - product.getPrice();
        order.setTotalCost(totalPrice);
        order.getProducts().remove(product);
        orderRepository.save(order);
    }

    public List<Product> viewProductsOfOrder(int orderID){
        Orders order = orderRepository.getOrderById(orderID);
        return order.getProducts();
    }

    public List<Orders> viewAllOrdersByUser(int userID){
        List<Orders> userOrders = new ArrayList<Orders>();
        List<Orders> allOrders = orderRepository.findAll();

        for (int i = 0; i < allOrders.size(); i++) {
            if(allOrders.get(i).getUserID() ==userID ) {
                userOrders.add(allOrders.get(i));
            }
        }
        return userOrders;
    }
}
