package com.charlieWoof.charlieBot.data.jpa;

import com.charlieWoof.charlieBot.data.entity.UserOrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOrderDetailsJPA extends JpaRepository<UserOrderDetails,Integer> {
}
