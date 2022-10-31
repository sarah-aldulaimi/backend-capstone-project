package com.capstone.ecommerce.service;
import com.capstone.ecommerce.exception.*;
import com.capstone.ecommerce.model.Order;
import com.capstone.ecommerce.model.Product;
import com.capstone.ecommerce.repository.OrderRepository;
import com.capstone.ecommerce.repository.ProductRepository;
import com.capstone.ecommerce.repository.UserRepository;
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
    @Autowired
    UserRepository userRepository;
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
    public Order getOrderById(int id) {
        orderRepository.findById(id).orElseThrow(()->{
            throw new NotFoundException("This order cannot be found");
        });
        return orderRepository.getOrderById(id);
    }
    public int updateOrder(int id, Order newOrder) {
        orderRepository.findById(id).orElseThrow(()->{
            throw new NotFoundException("This order cannot be found");
        });
        userRepository.findById(newOrder.getUserID()).orElseThrow(()->{
            throw new NotFoundException("This user cannot be found");
        });
        if(newOrder.getStatus().matches("[0-9]+")){
            throw new InvalidInputException("The status cannot contain any numbers");
        }
        if(newOrder.getTotalCost() < 0){
            throw new InvalidInputException("The total cost cannot be a negative number");
        }
        newOrder.setProducts(orderRepository.getOrderById(id).getProducts());
        orderRepository.save(newOrder);
        return newOrder.getId();
    }
    public void deleteOrderById (int id){
        orderRepository.findById(id).orElseThrow(()->{
            throw new NotFoundException("This order cannot be found");
        });
        orderRepository.deleteById(id);
    }
    public int addOrder(Order order){
        int orderNumber = gen();
        order.setOrderNumber(orderNumber);
        if(orderRepository.existsById(order.getId())){
            throw new AlreadyExistsException("This order already exists");
        }
        userRepository.findById(order.getUserID()).orElseThrow(()->{
            throw new NotFoundException("This user cannot be found");
        });
        orderRepository.save(order);
        return order.getId();
    }
    public int gen() {
        Random r = new Random( System.currentTimeMillis() );
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }
    public void addProductToOrder(int productID, int orderID, int productCount){
        orderRepository.findById(orderID).orElseThrow(()->{
            throw new NotFoundException("This order cannot be found");
        });
        float totalPrice = orderRepository.getOrderById(orderID).getTotalCost() + (productRepository.getProductById(productID).getPrice() * productCount);
        orderRepository.getOrderById(orderID).setTotalCost(totalPrice);
        orderRepository.getOrderById(orderID).getProducts().add(productRepository.getProductById(productID));
        int newProductCount = orderRepository.getOrderById(orderID).getProductCount() + productCount;
        orderRepository.getOrderById(orderID).setProductCount(newProductCount);
        orderRepository.save(orderRepository.getOrderById(orderID));
    }
    public void deleteProductFromOrder(int orderID, int productID, int productCount){
        orderRepository.findById(orderID).orElseThrow(()->{
            throw new NotFoundException("This order cannot be found");
        });
        productRepository.findById(productID).orElseThrow(()->{
            throw new NotFoundException("This product cannot be found");
        });
        if(!orderRepository.findAll().stream().anyMatch(order -> order.getProducts().contains(productRepository.getProductById(productID)))){
            throw new NotFoundException("This product is not a part of this order");
        }

        float totalPrice = orderRepository.getOrderById(orderID).getTotalCost() - (productRepository.getProductById(productID).getPrice() * productCount);
        orderRepository.getOrderById(orderID).setTotalCost(totalPrice);
        orderRepository.getOrderById(orderID).getProducts().remove(productRepository.getProductById(productID));
        int newProductCount = orderRepository.getOrderById(orderID).getProductCount() - productCount;
        orderRepository.getOrderById(orderID).setProductCount(newProductCount);
        orderRepository.save(orderRepository.getOrderById(orderID));
    }
    public List<Product> viewProductsOfOrder(int orderID){
        orderRepository.findById(orderID).orElseThrow(()->{
            throw new NotFoundException("This order cannot be found");
        });
        return orderRepository.getOrderById(orderID).getProducts();
    }
    public List<Order> viewAllOrdersByUser(int userID){
        userRepository.findById(userID).orElseThrow(()->{
            throw new NotFoundException("This user cannot be found");
        });
        return orderRepository.findAll().stream().filter(order-> order.getUserID() == userID).toList();
    }
}
