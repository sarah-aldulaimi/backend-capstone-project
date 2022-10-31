package com.capstone.ecommerce.repository;
import com.capstone.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductRepository extends JpaRepository<Product, Integer> {
    public Product getProductById(Integer id);
}

