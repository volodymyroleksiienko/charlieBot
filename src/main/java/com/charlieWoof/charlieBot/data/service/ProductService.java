package com.charlieWoof.charlieBot.data.service;

import java.awt.print.Pageable;
import java.util.List;

import com.charlieWoof.charlieBot.data.StatusOfEntity;
import com.charlieWoof.charlieBot.data.entity.Product;

public interface ProductService {
    void save(Product product);
    Product findById(int id);
    List<Product> findAll();
    List<Product> getProductsByCategoryId(int categoryId);
    List<Product> findByStatus(StatusOfEntity status);
//    List<Product> getPaginatedProducts(int step,int lastNumber,Pageable pageable);
    void deleteByID(int id);
}
