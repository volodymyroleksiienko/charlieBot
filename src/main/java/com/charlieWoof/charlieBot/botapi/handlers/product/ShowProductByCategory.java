package com.charlieWoof.charlieBot.botapi.handlers.product;

import com.charlieWoof.charlieBot.CharlieWebhookBot;
import com.charlieWoof.charlieBot.botapi.BotState;
import com.charlieWoof.charlieBot.botapi.InputMessageHandler;
import com.charlieWoof.charlieBot.cache.UserDataCache;
import com.charlieWoof.charlieBot.cache.UserInfoCache;
import com.charlieWoof.charlieBot.data.entity.Product;
import com.charlieWoof.charlieBot.data.service.ProductService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

@Component
public class ShowProductByCategory implements InputMessageHandler {
    private CharlieWebhookBot  charlieWebhookBot;
    private UserDataCache userDataCache;
    private ProductService productService;

    public ShowProductByCategory(@Lazy CharlieWebhookBot charlieWebhookBot, UserDataCache userDataCache, ProductService productService) {
        this.charlieWebhookBot = charlieWebhookBot;
        this.userDataCache = userDataCache;
        this.productService = productService;
    }

    @Override
    public SendMessage handle(Message message) {
        showProducts(message);
        return null;
    }

    private void showProducts(Message message) {
        System.out.println("flkas gk sgagkj afj");
        int userId = message.getFrom().getId();
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
                charlieWebhookBot.execute(configureProductMessage(message,products.get(i)));
            }catch (Exception e){
                e.printStackTrace();
            }

        }


    }

    private SendMessage configureProductMessage(Message message,Product product){
       long chatId = message.getChatId();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(product.getName()+product.getImgUrl());
        return sendMessage;
    }


    @Override
    public BotState getHandlerName() {
        return BotState.SHOW_PRODUCTS_BY_CATEGORY;
    }
}
