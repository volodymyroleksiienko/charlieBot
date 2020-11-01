package com.charlieWoof.charlieBot.service;

import com.amazonaws.services.dynamodbv2.xspec.S;
import com.charlieWoof.charlieBot.CharlieWebhookBot;
import com.charlieWoof.charlieBot.cache.BucketCache;
import com.charlieWoof.charlieBot.cache.UserDataCache;
import com.charlieWoof.charlieBot.cache.UserInfoCache;
import com.charlieWoof.charlieBot.data.entity.Product;
import com.charlieWoof.charlieBot.data.service.AmazonClientService;
import com.charlieWoof.charlieBot.data.service.ProductService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
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

                String mainBody="";
                String name = "<b>"+products.get(i).getName()+"</b>\n";
                String price = "<b>Ціна</b> "+products.get(i).getPrice() + " грн\n";
                String description = products.get(i).getDescription();
                String img = "<a href=\""+products.get(i).getImgUrl()+"\">&#8205;</a>";

                mainBody = name + price + description + img;

                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setParseMode("html");
                sendMessage.setText(mainBody);
                sendMessage.setReplyMarkup(getInlineMessageButtons("Додати в корзину",products.get(i)));
                charlieWebhookBot.execute(sendMessage);

               }catch (Exception e){
                e.printStackTrace();
            }

        }

    }

    public BotApiMethod<?> handleCallbackQuery(CallbackQuery buttonQuery){
        final long chatId = buttonQuery.getMessage().getChatId();
        final int userId = buttonQuery.getFrom().getId();
        BotApiMethod<?> callBackAnswer = null;

        InlineKeyboardMarkup keyboardMarkup = buttonQuery.getMessage().getReplyMarkup();
        int count = Integer.parseInt(keyboardMarkup.getKeyboard().get(0).get(1).getText());


        if (buttonQuery.getData().contains("addToBucket")) {
            int id = Integer.parseInt(buttonQuery.getData().replace("addToBucket",""));
            Product product = productService.findById(id);
            System.out.println(product);
            userDataCache.getUserInfoCache(userId).getProductList().add(new BucketCache(count,product));
            System.out.println(userDataCache.getUserInfoCache(userId).getProductList());
        }

        if (buttonQuery.getData().equals("inc") || buttonQuery.getData().equals("dec")){
            EditMessageReplyMarkup replyMarkup = new EditMessageReplyMarkup();
            replyMarkup.setChatId(chatId);
            replyMarkup.setMessageId(buttonQuery.getMessage().getMessageId());

            if(buttonQuery.getData().equals("inc")) {
                keyboardMarkup.getKeyboard().get(0).get(1).setText((count + 1) + "");
            }else {
                if(count>1){
                    keyboardMarkup.getKeyboard().get(0).get(1).setText((count - 1) + "");
                }
            }
            replyMarkup.setReplyMarkup(keyboardMarkup);
            try {
             charlieWebhookBot.execute(replyMarkup);
            }catch (TelegramApiException e){
                e.printStackTrace();
            }
        }


        return null;
    }


    private InlineKeyboardMarkup getInlineMessageButtons(String text,Product product) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List <List<InlineKeyboardButton>> rowsInline = new ArrayList<List<InlineKeyboardButton>>();

        List < InlineKeyboardButton > setCountInline = new ArrayList <InlineKeyboardButton> ();
        InlineKeyboardButton increment = new InlineKeyboardButton().setText("+");
        InlineKeyboardButton count = new InlineKeyboardButton().setText("1");
        InlineKeyboardButton decrement = new InlineKeyboardButton().setText("-");

        increment.setCallbackData("inc");
        count.setCallbackData("count");
        decrement.setCallbackData("dec");

        setCountInline.add(decrement);
        setCountInline.add(count);
        setCountInline.add(increment);

        List < InlineKeyboardButton > addToBucketInline = new ArrayList <InlineKeyboardButton> ();
        InlineKeyboardButton addToBucket = new InlineKeyboardButton().setText(text);
        addToBucket.setCallbackData("addToBucket"+product.getId());
        addToBucketInline.add(addToBucket);

        rowsInline.add(setCountInline);
        rowsInline.add(addToBucketInline);

        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }
}
