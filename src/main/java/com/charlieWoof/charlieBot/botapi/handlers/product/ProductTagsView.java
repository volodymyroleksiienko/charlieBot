package com.charlieWoof.charlieBot.botapi.handlers.product;

import com.charlieWoof.charlieBot.botapi.BotState;
import com.charlieWoof.charlieBot.botapi.InputMessageHandler;
import com.charlieWoof.charlieBot.data.entity.Category;
import com.charlieWoof.charlieBot.data.service.CategoryService;
import com.charlieWoof.charlieBot.service.ReplyMessagesService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductTagsView implements InputMessageHandler {
    private CategoryService categoryService;
    private ReplyMessagesService messagesService;

    public ProductTagsView(CategoryService categoryService, ReplyMessagesService messagesService) {
        this.categoryService = categoryService;
        this.messagesService = messagesService;
    }

    @Override
    public SendMessage handle(Message message) {
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SHOW_CATEGORY;
    }

    private SendMessage processUsersInput(Message inputMsg) {
        int userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();

        SendMessage replyToUser = messagesService.getReplyMessage(chatId,"reply.chooseCategory");
//        userDataCache.setUsersCurrentBotState(userId,BotState.FILLING_PROFILE);
        replyToUser.setReplyMarkup(getInlineMessageButtons("Відкрити","https://www.charliewoof.com/kursi-grumingu"));

        return replyToUser;
    }

    private InlineKeyboardMarkup getInlineMessageButtons(String text, String url) {
        List<Category> categoryList = categoryService.findAll();

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List <List<InlineKeyboardButton>> rowsInline = new ArrayList<List<InlineKeyboardButton>>();

        for (Category category:categoryList) {
            List < InlineKeyboardButton > rowInline = new ArrayList <> ();
            rowInline.add(new InlineKeyboardButton().setText(category.getName()).setCallbackData("categoryId"+category.getId()));
            rowsInline.add(rowInline);
        }

        markupInline.setKeyboard(rowsInline);

        return markupInline;
    }
}
