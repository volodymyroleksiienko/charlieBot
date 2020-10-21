package com.charlieWoof.charlieBot.cache;

import com.charlieWoof.charlieBot.data.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoCache {
    int chosenCategoryId;
    int firstIndexOfProduct;

    public UserInfoCache(int chosenCategoryId, int firstIndexOfProduct, int lastIndexOfProduct) {
        this.chosenCategoryId = chosenCategoryId;
        this.firstIndexOfProduct = firstIndexOfProduct;
        this.lastIndexOfProduct = lastIndexOfProduct;
    }

    int lastIndexOfProduct;

    List<Product> productList;

    public List<Product> getProductList() {
        if (productList==null){
            productList = new ArrayList<>();
        }
        return productList;
    }
}
