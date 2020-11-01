package com.charlieWoof.charlieBot.botapi.handlers.product;

import com.charlieWoof.charlieBot.botapi.BotState;
import com.charlieWoof.charlieBot.botapi.InputMessageHandler;
import com.charlieWoof.charlieBot.botapi.handlers.fillingprofile.UserProfileData;
import com.charlieWoof.charlieBot.cache.UserDataCache;
import com.charlieWoof.charlieBot.cache.UserInfoCache;
import com.charlieWoof.charlieBot.service.ReplyMessagesService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class FillingOrderDetails implements InputMessageHandler {
    private UserDataCache userDataCache;
    private ReplyMessagesService messagesService;

    public FillingOrderDetails(UserDataCache userDataCache,
                                 ReplyMessagesService messagesService) {
        this.userDataCache = userDataCache;
        this.messagesService = messagesService;
    }

    @Override
    public SendMessage handle(Message message) {
        if (userDataCache.getUsersCurrentBotState(message.getFrom().getId()).equals(BotState.FILLING_PROFILE)) {
            userDataCache.setUsersCurrentBotState(message.getFrom().getId(), BotState.ASK_NAME);
        }
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.FILLING_PROFILE;
    }

    private SendMessage processUsersInput(Message inputMsg) {
        String usersAnswer = inputMsg.getText();
        int userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();

        UserInfoCache userInfo = userDataCache.getUserInfoCache(userId);
        BotState botState = userDataCache.getUsersCurrentBotState(userId);

        SendMessage replyToUser = null;

        if (botState.equals(BotState.ASK_ORDER_NAME)) {
            replyToUser = messagesService.getReplyMessage(chatId, "reply.askName");
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_ORDER_PHONE);
        }

        if (botState.equals(BotState.ASK_ORDER_PHONE)) {
            userInfo.setPhone(usersAnswer);
            replyToUser = messagesService.getReplyMessage(chatId, "reply.askPhone");
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_NUMBER);
        }

        if (botState.equals(BotState.ASK_ORDER_LOCATION)) {
            userInfo.setName(usersAnswer);
            replyToUser = messagesService.getReplyMessage(chatId, "reply.askLocation");
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_GENDER);
        }



//        if (botState.equals(BotState.PROFILE_FILLED)) {
//            profileData.setSong(usersAnswer);
//            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_DESTINY);
//            replyToUser = new SendMessage(chatId, String.format("%s %s", "Данные по вашей анкете", profileData));
//        }

        userDataCache.saveUserInfoCache(userId,userInfo);
        return replyToUser;
    }
}
