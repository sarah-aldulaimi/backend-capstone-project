package com.capstone.ecommerce.controller;
import com.capstone.ecommerce.model.Category;
import com.capstone.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
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
    private Category getCategoryByID(@PathVariable("categoryID") int categoryID){
       return categoryService.getCategoryById(categoryID);
    }
    @DeleteMapping("/{categoryID}")
    private ResponseEntity deleteCategory(@PathVariable("categoryID") int categoryID) {
        categoryService.deleteCategoryById(categoryID);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping
    private ResponseEntity saveCategory(@RequestBody Category category)  {
        int id = categoryService.addCategory(category);
        return new ResponseEntity(id, HttpStatus.CREATED);
    }
    @PutMapping("/{categoryID}")
    private ResponseEntity updateCategory(@PathVariable("categoryID") int categoryID, @RequestBody Category category){
        categoryService.updateCategory(categoryID, category);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
