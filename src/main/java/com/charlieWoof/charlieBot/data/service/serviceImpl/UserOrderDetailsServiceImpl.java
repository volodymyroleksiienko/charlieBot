package com.charlieWoof.charlieBot.data.service.serviceImpl;

import com.charlieWoof.charlieBot.data.entity.UserOrderDetails;
import com.charlieWoof.charlieBot.data.jpa.UserOrderDetailsJPA;
import com.charlieWoof.charlieBot.data.service.UserOrderDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOrderDetailsServiceImpl implements UserOrderDetailsService {
    private UserOrderDetailsJPA  userOrderDetailsJPA;

    public UserOrderDetailsServiceImpl(UserOrderDetailsJPA userOrderDetailsJPA) {
        this.userOrderDetailsJPA = userOrderDetailsJPA;
    }

    @Override
    public void save(UserOrderDetails userOrderDetails) {
        userOrderDetailsJPA.save(userOrderDetails);
    }

    @Override
    public UserOrderDetails findById(int id) {
        return userOrderDetailsJPA.getOne(id);
    }

    @Override
    public List<UserOrderDetails> findAll() {
        return userOrderDetailsJPA.findAll();
    }

    @Override
    public void deleteByID(int id) {
        userOrderDetailsJPA.deleteById(id);
    }
}
