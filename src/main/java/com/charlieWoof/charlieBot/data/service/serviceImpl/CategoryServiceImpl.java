package com.charlieWoof.charlieBot.data.service.serviceImpl;

import com.charlieWoof.charlieBot.data.StatusOfEntity;
import com.charlieWoof.charlieBot.data.entity.Category;
import com.charlieWoof.charlieBot.data.entity.Product;
import com.charlieWoof.charlieBot.data.jpa.CategoryJPA;
import com.charlieWoof.charlieBot.data.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryJPA categoryJPA;

    @Autowired
    public CategoryServiceImpl(CategoryJPA categoryJPA) {
        this.categoryJPA = categoryJPA;
    }

    @Override
    public void save(Category category) {
        categoryJPA.save(category);
    }

    @Override
    public Category findById(int id) {
        return categoryJPA.getOne(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryJPA.findAll();
    }

    @Override
    public List<Category> findByStatus(StatusOfEntity status) {
        return categoryJPA.getCategoriesByStatus(status);
    }

    @Override
    public void deleteByID(int id) {
        categoryJPA.deleteById(id);
    }
}
