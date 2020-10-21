package com.charlieWoof.charlieBot.data.jpa;

import com.charlieWoof.charlieBot.data.StatusOfEntity;
import com.charlieWoof.charlieBot.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryJPA extends JpaRepository<Category,Integer> {
    @Query("select obj from Category obj where obj.statusOfEntity=?1")
    List<Category> getCategoriesByStatus(StatusOfEntity status);

}
