package com.charlieWoof.charlieBot.data.entity;

import com.amazonaws.services.dynamodbv2.xspec.S;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserOrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private long chatId;

    private String phone;
    private String location;
    private String name;
}
