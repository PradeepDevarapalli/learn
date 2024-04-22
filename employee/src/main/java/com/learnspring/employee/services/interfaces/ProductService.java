package com.learnspring.employee.services.interfaces;

import com.learnspring.employee.entity.Product;

import java.util.concurrent.ExecutionException;

public interface ProductService {
    String createProductAsynchronously(Product product) throws Exception;
    String createProductSynchronously(Product product) throws Exception;
}
