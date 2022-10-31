package com.capstone.ecommerce.controller;
import com.capstone.ecommerce.model.Product;
import com.capstone.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping
    private List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("/{productID}")
    private Product getProductByID(@PathVariable("productID") int productID) {
        return productService.getProductById(productID);
    }
    @DeleteMapping("/{productID}")
    private ResponseEntity deleteProduct(@PathVariable("productID") int productID) {
        productService.deleteProductById(productID);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping
    private ResponseEntity saveProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @PutMapping("/{productID}")
    private ResponseEntity updateProduct(@PathVariable("productID") int productID, @RequestBody Product product){
         productService.updateProduct(productID, product);
         return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @GetMapping("categories/{categoryID}")
    private List<Product> getFilteredProductsByCategory(@PathVariable("categoryID") int categoryID){
        return productService.getFilteredProducts(categoryID);
    }
}
