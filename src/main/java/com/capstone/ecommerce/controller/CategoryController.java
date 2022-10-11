package com.capstone.ecommerce.controller;

import com.capstone.ecommerce.model.Category;
import com.capstone.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    private List<Category> getAllOrders(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{categoryID}")
    private Category getCategoryByID(@PathVariable("categoryID") int categoryID) {
        return categoryService.getCategoryById(categoryID);
    }

    @DeleteMapping("/{categoryID}")
    private void deleteCategory(@PathVariable("categoryID") int categoryID) {
        categoryService.deleteCategoryById(categoryID);
    }

    @PostMapping
    private Category saveCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @PutMapping("/{categoryID}")
    private void updateCategory(@PathVariable("categoryID") int categoryID, @RequestBody Category category){
        categoryService.updateCategory(categoryID, category);
    }

}
