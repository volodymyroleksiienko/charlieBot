package com.charlieWoof.charlieBot.controllers;


import com.amazonaws.services.dynamodbv2.xspec.S;
import com.charlieWoof.charlieBot.data.entity.Category;
import com.charlieWoof.charlieBot.data.entity.Product;
import com.charlieWoof.charlieBot.data.service.AmazonClientService;
import com.charlieWoof.charlieBot.data.service.CategoryService;
import com.charlieWoof.charlieBot.data.service.ProductService;
import com.charlieWoof.charlieBot.data.service.serviceImpl.AmazonClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private AmazonClientServiceImpl amazonClientService;
    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public AdminController(AmazonClientServiceImpl amazonClientService, ProductService productService,
                           CategoryService categoryService) {
        this.amazonClientService = amazonClientService;
        this.productService =  productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/allProducts")
    public String allProducts(Model model){
        model.addAttribute("objectList",productService.findAll());
        model.addAttribute("fragmentPathTabConfig","adminDashboard");
        model.addAttribute("fragmentPathProducts","fragProducts");
        model.addAttribute("tabName","products");

        model.addAttribute("infoBtnOn","true");

        model.addAttribute("urlAdd","/admin/addProduct");
        model.addAttribute("urlInfo","/admin/getInfoProduct");
        model.addAttribute("urlEdit","/admin/editProduct");
        model.addAttribute("urlDelete","/admin/deleteProduct");

        return "adminPage";
    }

    @PostMapping("/addProduct")
    public String addProduct(Product product){
        productService.save(product);
        return "redirect:/admin/allProducts";
    }
    @GetMapping("/getInfoProduct-{id}")
    public String getInfoProduct(@PathVariable("id") int id,Model model){
        model.addAttribute("product",productService.findById(id));
        model.addAttribute("categoryList",categoryService.findAll());

        model.addAttribute("fragmentPathTabConfig","adminDashboard");
        model.addAttribute("fragmentPathProducts","fragInfoProducts");
        model.addAttribute("tabName","products");
        return "adminPage";
    }

    @PostMapping("/editInfoProduct")
    public String editInfoProduct(Product product, MultipartFile file) throws IOException {
        if (file!=null){
            String url = amazonClientService.uploadFile(file);
            product.setImgUrl(url);
        }
        productService.save(product);
        return "redirect:/admin/allProducts";
    }

    @PostMapping("/editProduct")
    public String editProduct(Product product){
        productService.save(product);
        return "redirect:/admin/allProducts";
    }
    @PostMapping("/deleteProduct")
    public String deleteProduct(String id){
        productService.deleteByID(Integer.parseInt(id));
        return "redirect:/admin/allProducts";
    }

//    CATEGORIES

    @GetMapping("/allCategories")
    public String allCategories(Model model){
        model.addAttribute("objectList",categoryService.findAll());
        model.addAttribute("fragmentPathTabConfig","adminDashboard");
        model.addAttribute("fragmentPathProducts","fragProducts");
        model.addAttribute("tabName","category");

        model.addAttribute("urlAdd","/admin/addCategory");
        model.addAttribute("urlEdit","/admin/editCategory");
        model.addAttribute("urlDelete","/admin/deleteCategory");
        return "adminPage";
    }

    @PostMapping("/addCategory")
    public String addCategory(Category category){
       categoryService.save(category);
       return "redirect:/admin/allCategories";
    }

    @PostMapping("/editCategory")
    public String editCategory(Category category){
        categoryService.save(category);
        return "redirect:/admin/allCategories";
    }
    @PostMapping("/deleteCategory")
    public String deleteCategory(String id){
        categoryService.deleteByID(Integer.parseInt(id));
        return "redirect:/admin/allCategories";
    }


    @PostMapping("/uploadFile")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) throws IOException {
        return this.amazonClientService.uploadFile(file);
    }

    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonClientService.deleteFileFromS3Bucket(fileUrl);
    }
}
