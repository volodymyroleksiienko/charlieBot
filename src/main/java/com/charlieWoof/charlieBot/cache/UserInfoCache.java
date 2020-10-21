package com.charlieWoof.charlieBot.cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoCache {
    int chosenCategoryId;
    int firstIndexOfProduct;
    int lastIndexOfProduct;
}
