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
    private int chosenCategoryId;
    private int firstIndexOfProduct;
    private int lastIndexOfProduct;

    private List<Product> productList;

    private String name;
    private String location;
    private String phone;


    public UserInfoCache(int chosenCategoryId, int firstIndexOfProduct, int lastIndexOfProduct) {
        this.chosenCategoryId = chosenCategoryId;
        this.firstIndexOfProduct = firstIndexOfProduct;
        this.lastIndexOfProduct = lastIndexOfProduct;
    }

    public List<Product> getProductList() {
        if (productList==null){
            productList = new ArrayList<>();
        }
        return productList;
    }
}
