package com.charlieWoof.charlieBot.data.entity;

import com.charlieWoof.charlieBot.data.service.OrderStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double totalPrice;


    @ManyToOne
    private UserOrderDetails userOrderDetails;
    @ManyToMany
    private List<Product> productList;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
