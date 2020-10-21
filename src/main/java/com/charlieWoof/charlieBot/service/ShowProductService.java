package com.charlieWoof.charlieBot.service;

import com.charlieWoof.charlieBot.CharlieWebhookBot;
import com.charlieWoof.charlieBot.cache.UserDataCache;
import com.charlieWoof.charlieBot.cache.UserInfoCache;
import com.charlieWoof.charlieBot.data.entity.Product;
import com.charlieWoof.charlieBot.data.service.AmazonClientService;
import com.charlieWoof.charlieBot.data.service.ProductService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

@Service
public class ShowProductService {
    private CharlieWebhookBot charlieWebhookBot;
    private UserDataCache userDataCache;
    private ProductService productService;
    private AmazonClientService amazonClientService;

    public ShowProductService(@Lazy CharlieWebhookBot charlieWebhookBot, UserDataCache userDataCache,
                              ProductService productService, AmazonClientService amazonClientService) {
        this.charlieWebhookBot = charlieWebhookBot;
        this.userDataCache = userDataCache;
        this.productService = productService;
        this.amazonClientService = amazonClientService;
    }

    public void showProducts(long chatId,int userId) {
        System.out.println("flkas gk sgagkj afj");
        UserInfoCache userInfoCache = userDataCache.getUserInfoCache(userId);
        if (userInfoCache.getChosenCategoryId()==0){
            return;
        }
        int firstIndex = userInfoCache.getFirstIndexOfProduct();
        int lastIndex = userInfoCache.getLastIndexOfProduct();
        List<Product> products = productService.getProductsByCategoryId(userInfoCache.getChosenCategoryId());

        for(int i = firstIndex; i<=lastIndex;i++){
            if(i==products.size()){
                break;
            }
            try{
                charlieWebhookBot.execute(configureProductMessage(chatId,products.get(i)));
            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }

    private SendMessage configureProductMessage(long chatId, Product product){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(product.getName());
//        sendMessage.setCaption(product.getName());
//        sendMessage.setPhoto(product.getImgUrl());

        SendDocument sendPhoto = new SendDocument();
        sendPhoto.setChatId(chatId);
        try{
            sendPhoto.setDocument("classpath:/img/pricingImg/pricing1.jpg");
        }catch (Exception e){
            e.printStackTrace();
        }
        return sendMessage;
    }

}
