package com.charlieWoof.charlieBot.data.service.serviceImpl;


import com.charlieWoof.charlieBot.data.StatusOfEntity;
import com.charlieWoof.charlieBot.data.entity.Category;
import com.charlieWoof.charlieBot.data.entity.Product;
import com.charlieWoof.charlieBot.data.jpa.ProductJPA;
import com.charlieWoof.charlieBot.data.service.AmazonClientService;
import com.charlieWoof.charlieBot.data.service.CategoryService;
import com.charlieWoof.charlieBot.data.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductJPA productJPA;
    private AmazonClientService amazonClientService;
    private CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductJPA productJPA, AmazonClientService amazonClientService, CategoryService categoryService) {
        this.productJPA = productJPA;
        this.amazonClientService = amazonClientService;
        this.categoryService = categoryService;
    }

    @Override
    public void save(Product product) {
        Product productDB = productJPA.getOne(product.getId());

        if(productDB.getCategory()!=null){
            Category category = categoryService.findById(productDB.getCategory().getId());
            category.getProductList().remove(productDB);
            categoryService.save(category);
        }

        if(product.getCategory()!=null){
            Category category = categoryService.findById(product.getCategory().getId());
            category.getProductList().add(product);
            categoryService.save(category);
        }

        productJPA.save(product);
    }

    @Override
    public Product findById(int id) {
        return productJPA.getOne(id);
    }

    @Override
    public List<Product> findAll() {
        return productJPA.findAll();
    }

    @Override
    public List<Product> getProductsByCategoryId(int categoryId) {
        return productJPA.getProductsByCategoryId(categoryId);
    }

    @Override
    public List<Product> findByStatus(StatusOfEntity status) {
        return productJPA.getProductsByStatus(status);
    }

//    @Override
//    public List<Product> getPaginatedProducts(int step, int firstElement, Pageable pageable) {
//        Pageable pageRequest = (Pageable) PageRequest.of(firstElement, firstElement+step);
//        return productJPA;
//    }

    @Override
    public void deleteByID(int id) {
        amazonClientService.deleteFileFromS3Bucket(
                productJPA.getOne(id).getImgUrl()
        );
        productJPA.deleteById(id);
    }
}
