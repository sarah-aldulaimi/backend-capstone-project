package com.capstone.ecommerce.repository;

import com.capstone.ecommerce.model.Category;
import com.capstone.ecommerce.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    public Category getCategoryById(Integer id);
}

