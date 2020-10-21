package com.charlieWoof.charlieBot.data.service;

import com.charlieWoof.charlieBot.data.StatusOfEntity;
import com.charlieWoof.charlieBot.data.entity.OrderInfo;
import com.charlieWoof.charlieBot.data.entity.Product;

import java.util.List;

public interface OrderService {
    void save(OrderInfo orderInfo);
    OrderInfo findById(int id);
    List<Product> findAll();
    List<Product> getProductsByCategoryId(int categoryId);
    List<Product> findByStatus(StatusOfEntity status);
    void deleteByID(int id);
}
