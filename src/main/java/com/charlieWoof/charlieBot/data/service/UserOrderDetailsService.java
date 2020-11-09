package com.charlieWoof.charlieBot.data.service;

import com.charlieWoof.charlieBot.data.StatusOfEntity;
import com.charlieWoof.charlieBot.data.entity.OrderInfo;
import com.charlieWoof.charlieBot.data.entity.Product;
import com.charlieWoof.charlieBot.data.entity.UserOrderDetails;

import java.util.List;

public interface UserOrderDetailsService {
    void save(UserOrderDetails  userOrderDetails);
    UserOrderDetails findById(int id);
    List<UserOrderDetails> findAll();
    void deleteByID(int id);
}
