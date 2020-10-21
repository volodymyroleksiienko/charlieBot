package com.charlieWoof.charlieBot.botapi.handlers.product;

import com.charlieWoof.charlieBot.botapi.BotState;
import com.charlieWoof.charlieBot.botapi.InputMessageHandler;
import com.charlieWoof.charlieBot.cache.UserDataCache;
import com.charlieWoof.charlieBot.data.entity.Product;
import com.charlieWoof.charlieBot.service.ReplyMessagesService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShowBucketInfo implements InputMessageHandler {
    private UserDataCache userDataCache;
    private ReplyMessagesService messagesService;

    public ShowBucketInfo(UserDataCache userDataCache,
                     ReplyMessagesService messagesService) {
        this.userDataCache = userDataCache;
        this.messagesService = messagesService;
    }

    @Override
    public SendMessage handle(Message message) {
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SHOW_BUCKET_INFO;
    }

    private SendMessage processUsersInput(Message inputMsg) {
        int userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();
        System.out.println(userId);
        List<Product> productList = userDataCache.getUserInfoCache(userId).getProductList();
        SendMessage replyToUser;
        if(productList.size()==0){
            replyToUser = messagesService.getReplyMessage(chatId,"reply.askSignUp");
        }else {
            replyToUser = new SendMessage();
            replyToUser.setChatId(chatId);

            String text="";
            for (Product product:productList) {
                text +="\n"+product.getName();
            }
            replyToUser.setText(text);
            replyToUser.setReplyMarkup(getInlineMessageButtons());
        }


        return replyToUser;
    }

    private InlineKeyboardMarkup getInlineMessageButtons() {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List <List<InlineKeyboardButton>> rowsInline = new ArrayList<List<InlineKeyboardButton>>();
        List < InlineKeyboardButton > rowInline1 = new ArrayList <InlineKeyboardButton> ();
        List < InlineKeyboardButton > rowInline2 = new ArrayList <InlineKeyboardButton> ();
        InlineKeyboardButton orderBtn = new InlineKeyboardButton().setText("Оформити замовлення");
        InlineKeyboardButton clearBtn = new InlineKeyboardButton().setText("Очистити кошик");

        orderBtn.setCallbackData("toDoOrder");
        clearBtn.setCallbackData("clearBucketInfo");

        rowInline1.add(orderBtn);
        rowInline2.add(clearBtn);

        rowsInline.add(rowInline1);
        rowsInline.add(rowInline2);

        markupInline.setKeyboard(rowsInline);

        return markupInline;
    }
}
