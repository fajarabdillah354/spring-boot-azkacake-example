package com.azka_cake_project.controller;


import com.azka_cake_project.entity.Payment;
import com.azka_cake_project.entity.Product;
import com.azka_cake_project.service.ProductService;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private ProductService productService;



    @GetMapping("/products")
    public List<Product> products(){
        return productService.products();
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }



    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteProduct(@PathVariable String name){
        return productService.deleteProduct(name);
    }

}
