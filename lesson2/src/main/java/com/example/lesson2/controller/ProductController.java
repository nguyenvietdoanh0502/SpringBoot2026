package com.example.lesson2.controller;


import com.example.lesson2.model.Product;
import com.example.lesson2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productRepository.getAllProducts();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productRepository.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product request){
        productRepository.addProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(request);
    }
    @PutMapping("/{id}")
    public void updateProductById(@PathVariable int id, @RequestBody Product product){
        productRepository.updateProductById(product,id);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable int id){
        productRepository.deleteProductById(id);
    }
}
