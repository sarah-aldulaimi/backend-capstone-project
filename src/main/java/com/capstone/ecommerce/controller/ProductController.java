package com.capstone.ecommerce.controller;

import com.capstone.ecommerce.model.Product;
import com.capstone.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
//    @CrossOrigin(origins = "https://localhost:4200")
    @DeleteMapping("/{productID}")
    private void deleteProduct(@PathVariable("productID") int productID) {
        productService.deleteProductById(productID);
    }

    @PostMapping
    private void saveProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @PutMapping("/{productID}")
    private void updateProduct(@PathVariable("productID") int productID, @RequestBody Product product){
        productService.updateProduct(productID, product);
    }

}
