package com.charlieWoof.charlieBot.botapi.handlers.askMainButtons;

import com.charlieWoof.charlieBot.botapi.BotState;
import com.charlieWoof.charlieBot.botapi.InputMessageHandler;
import com.charlieWoof.charlieBot.cache.UserDataCache;
import com.charlieWoof.charlieBot.service.ReplyMessagesService;
import com.charlieWoof.charlieBot.utils.Emojis;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class AskWebsite implements InputMessageHandler {
    private UserDataCache userDataCache;
    private ReplyMessagesService messagesService;

    public AskWebsite(UserDataCache userDataCache,
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
        return BotState.ASK_WEBSITE;
    }

    private SendMessage processUsersInput(Message inputMsg) {
        int userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();

        SendMessage replyToUser = messagesService.getReplyMessage(chatId,"reply.askWebsite", Emojis.PAW,Emojis.WINK_FACE);
//        userDataCache.setUsersCurrentBotState(userId,BotState.FILLING_PROFILE);
        replyToUser.setReplyMarkup(getInlineMessageButtons("Посилання","https://www.charliewoof.com/kursi-grumingu"));

        return replyToUser;
    }

    private InlineKeyboardMarkup getInlineMessageButtons(String text, String url) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List <List<InlineKeyboardButton>> rowsInline = new ArrayList<List<InlineKeyboardButton>>();
        List < InlineKeyboardButton > rowInline = new ArrayList <InlineKeyboardButton> ();
        rowInline.add(new InlineKeyboardButton().setText(text).setUrl(url));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);

        return markupInline;
    }
}