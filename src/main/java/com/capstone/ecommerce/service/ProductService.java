package com.capstone.ecommerce.service;

import com.capstone.ecommerce.model.Product;
import com.capstone.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Product updateProduct(int id, Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            productRepository.save(product);
        }
        return product;
    }
    public void deleteProductById (int id){
        productRepository.deleteById(id);
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }

    public List<Product> getFilteredProducts(int categoryID){
        List<Product> temp = new ArrayList<Product>();
        List<Product> filteredProducts = new ArrayList<Product>();
        temp = productRepository.findAll();
        for (int i = 0; i < temp.size(); i++) {
            if(temp.get(i).getCategoryID() == categoryID){
                filteredProducts.add(temp.get(i));
            }
        }

        return filteredProducts;
    }
}
