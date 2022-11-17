package com.capstone.ecommerce.service;

import com.capstone.ecommerce.exception.AlreadyExistsException;
import com.capstone.ecommerce.exception.InvalidInputException;
import com.capstone.ecommerce.exception.NotFoundException;
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
        if(!categoryRepository.existsById(id)){
            throw new NotFoundException("This category cannot be found");
        }
        return categoryRepository.getCategoryById(id);
    }

    public void updateCategory(int id, Category category) {
        categoryRepository.findById(id).orElseThrow(()->{
            throw new NotFoundException("This category cannot be found");
        });
        if(category.getName().matches("[0-9]+")){
            throw new InvalidInputException("The category name cannot be numeric");
        }
            category.setId(id);
            categoryRepository.save(category);
    }
    public void deleteCategoryById (int id){
        categoryRepository.findById(id).orElseThrow(()->{
            throw new NotFoundException("This category cannot be found");
        });
        categoryRepository.deleteById(id);
    }
    public int addCategory(Category category){
        if(categoryRepository.existsById(category.getId())){
            throw new AlreadyExistsException("This category already exists");
        }
        if(category.getName().matches("[0-9]+")){
            throw new InvalidInputException("Category names cannot include numbers");
        }
        categoryRepository.save(category);
        return category.getId();
    }
}
