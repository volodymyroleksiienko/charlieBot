package com.charlieWoof.charlieBot.data.jpa;

import com.charlieWoof.charlieBot.data.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  OrderJPA extends JpaRepository<OrderInfo,Integer> {
}
