package com.charlieWoof.charlieBot.data.jpa;

import com.charlieWoof.charlieBot.data.StatusOfEntity;
import com.charlieWoof.charlieBot.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductJPA extends JpaRepository<Product,Integer> {
    @Query("select obj from Product obj where obj.statusOfEntity=?1")
    List<Product> getProductsByStatus(StatusOfEntity status);

    @Query("select obj from Product obj where obj.category.id=?1")
    List<Product> getProductsByCategoryId(int categoryId);
}
