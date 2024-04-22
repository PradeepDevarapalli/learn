package com.learnspring.employee.controllers;

import com.learnspring.employee.entity.Product;
import com.learnspring.employee.exception.kafka.EventErrorResponse;
import com.learnspring.employee.services.impls.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductServiceImpl productService;
    @Autowired
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        String productId = null;
        try {
             productService.createProductAsynchronously(product);
        } catch( Exception e2) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new EventErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "Exception while Producing product event",
                            System.currentTimeMillis()));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }
}
