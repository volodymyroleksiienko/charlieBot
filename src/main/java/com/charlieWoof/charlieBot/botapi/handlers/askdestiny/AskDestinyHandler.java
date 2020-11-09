package com.charlieWoof.charlieBot.botapi.handlers.askdestiny;

import com.charlieWoof.charlieBot.botapi.BotState;
import com.charlieWoof.charlieBot.botapi.InputMessageHandler;
import com.charlieWoof.charlieBot.cache.UserDataCache;
import com.charlieWoof.charlieBot.data.entity.UserOrderDetails;
import com.charlieWoof.charlieBot.data.service.UserOrderDetailsService;
import com.charlieWoof.charlieBot.service.MainMenuService;
import com.charlieWoof.charlieBot.service.ReplyMessagesService;
import com.charlieWoof.charlieBot.utils.Emojis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;


/**
 * Спрашивает пользователя- хочет ли он получить предсказание.
 */

@Slf4j
@Component
public class AskDestinyHandler implements InputMessageHandler {
    private MainMenuService mainMenuService;
    private ReplyMessagesService messagesService;
    private UserOrderDetailsService userOrderDetailsService;

    public AskDestinyHandler(MainMenuService mainMenuService,
                             ReplyMessagesService messagesService, UserOrderDetailsService userOrderDetailsService) {
        this.mainMenuService = mainMenuService;
        this.messagesService = messagesService;
        this.userOrderDetailsService = userOrderDetailsService;
    }

    @Override
    public SendMessage handle(Message message) {
        UserOrderDetails  details = new UserOrderDetails();
        details.setChatId(message.getChatId());
        userOrderDetailsService.save(details);
        return mainMenuService.getMainMenuMessage(message.getChatId(), messagesService.getReplyText("reply.showMainMenu", Emojis.DOG,Emojis.CAT,Emojis.WINK_FACE));

    }

    @Override
    public BotState getHandlerName() {
        return BotState.START;
    }

}



