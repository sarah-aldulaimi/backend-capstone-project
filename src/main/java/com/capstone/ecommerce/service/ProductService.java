package com.capstone.ecommerce.service;

import com.capstone.ecommerce.model.Product;
import com.capstone.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.getProductById(id);
    }

        public void updateProduct(int id, Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            productRepository.save(product);
        }
    }
    public void deleteProductById (int id){
        productRepository.deleteById(id);
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }


}
