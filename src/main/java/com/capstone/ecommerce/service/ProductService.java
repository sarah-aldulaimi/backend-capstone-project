package com.capstone.ecommerce.service;
import com.capstone.ecommerce.exception.AlreadyExistsException;
import com.capstone.ecommerce.exception.DatabaseEmptyException;
import com.capstone.ecommerce.exception.InvalidInputException;
import com.capstone.ecommerce.exception.NotFoundException;
import com.capstone.ecommerce.model.Product;
import com.capstone.ecommerce.repository.CategoryRepository;
import com.capstone.ecommerce.repository.OrderRepository;
import com.capstone.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    OrderRepository orderRepository;
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Product getProductById(int id) {
        productRepository.findById(id).orElseThrow(()->{
            throw new NotFoundException("This product cannot be found");
        });
        return productRepository.getProductById(id);
    }
    public void updateProduct(int id, Product product) {
        productRepository.findById(id).orElseThrow(()->{
            throw new NotFoundException("This product cannot be found");
        });
        if(product.getPrice() < 0){
            throw new InvalidInputException("Price can not be negative");
        }
        categoryRepository.findById(product.getCategoryID()).orElseThrow(()->{
            throw new NotFoundException("This category cannot be found");
        });
        //product.setId(id);
        productRepository.save(product);
    }
    public void deleteProductById (int id){
        if(!productRepository.existsById(id)){
            throw new NotFoundException("This product cannot be found");
        }
/*      Not sure if I want to include this
        If a product is part of an order and the admin tries to delete an exception gets thrown
//        if(orderRepository.findAll().stream().anyMatch(order -> order.getProducts().contains(productRepository.getProductById(id)))){
//            throw new AlreadyExistsException();
        }
*/
        productRepository.deleteById(id);
    }
    public void addProduct(Product product){
        categoryRepository.findById(product.getCategoryID()).orElseThrow(()->{
            throw new NotFoundException("This category cannot be found");
        });
        if(productRepository.existsById(product.getId())){
            throw new AlreadyExistsException("This product already exists");
        }
        if(product.getPrice() < 0){
            throw new InvalidInputException("Price can not be negative");
        }
        productRepository.save(product);
    }
    public List<Product> getFilteredProducts(int categoryID){
        return productRepository.findAll().stream().filter(product -> product.getCategoryID() == categoryID).toList();
    }
}
