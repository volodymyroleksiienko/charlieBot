package com.charlieWoof.charlieBot.data.entity;

import com.charlieWoof.charlieBot.data.StatusOfEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    @Lob
    private String description;
    @Lob
    private String fullDescription;
    private String imgUrl;
    private double price;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    @Enumerated(EnumType.STRING)
    private StatusOfEntity statusOfEntity = StatusOfEntity.ACTIVE;
}
