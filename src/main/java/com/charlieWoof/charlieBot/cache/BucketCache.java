package com.charlieWoof.charlieBot.cache;

import com.charlieWoof.charlieBot.data.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BucketCache {
    private int count;
    private Product product;
}
