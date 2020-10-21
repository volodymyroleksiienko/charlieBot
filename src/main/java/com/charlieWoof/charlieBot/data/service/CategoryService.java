package com.charlieWoof.charlieBot.data.service;

import com.charlieWoof.charlieBot.data.StatusOfEntity;
import com.charlieWoof.charlieBot.data.entity.Category;

import java.util.List;

public interface CategoryService {
    void save(Category category);
    Category findById(int id);
    List<Category> findAll();
    List<Category> findByStatus(StatusOfEntity status);
    void deleteByID(int id);
}
