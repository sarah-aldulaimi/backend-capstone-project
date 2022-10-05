package com.capstone.ecommerce.service;

import com.capstone.ecommerce.model.Category;
import com.capstone.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(int id) {
        return categoryRepository.getCategoryById(id);
    }

    public void updateCategory(int id, Category category) {
        if (categoryRepository.existsById(id)) {
            category.setId(id);
            categoryRepository.save(category);
        }
    }
    public void deleteCategoryById (int id){
        categoryRepository.deleteById(id);
    }

    public Category addCategory(Category category){
        categoryRepository.save(category);
        return  category;
    }
}
