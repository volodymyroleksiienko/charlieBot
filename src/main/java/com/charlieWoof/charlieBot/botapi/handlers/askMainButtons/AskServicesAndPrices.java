package com.charlieWoof.charlieBot.botapi.handlers.askMainButtons;

import com.charlieWoof.charlieBot.CharlieWebhookBot;
import com.charlieWoof.charlieBot.botapi.BotState;
import com.charlieWoof.charlieBot.botapi.InputMessageHandler;
import com.charlieWoof.charlieBot.cache.UserDataCache;
import com.charlieWoof.charlieBot.data.service.AmazonClientService;
import com.charlieWoof.charlieBot.service.ReplyMessagesService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;

import java.util.ArrayList;
import java.util.List;

@Component
public class AskServicesAndPrices implements InputMessageHandler {
    private CharlieWebhookBot charlieWebhookBot;


    public AskServicesAndPrices(@Lazy CharlieWebhookBot charlieWebhookBot) {
        this.charlieWebhookBot = charlieWebhookBot;
    }

    @Override
    public SendMessage handle(Message message) {
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.ASK_PRICE;
    }

    private SendMessage processUsersInput(Message inputMsg) {
        long chatId = inputMsg.getChatId();


        SendMediaGroup sendMediaGroup = new SendMediaGroup();
        List<InputMedia> inputMediaPhotos = new ArrayList<>();
        InputMediaPhoto img1 = new InputMediaPhoto();
        InputMediaPhoto img2 = new InputMediaPhoto();
        InputMediaPhoto img3 = new InputMediaPhoto();
        InputMediaPhoto img4 = new InputMediaPhoto();
        InputMediaPhoto img5 = new InputMediaPhoto();

            img1.setMedia("https://charliebot.s3.eu-central-1.amazonaws.com/pricing1.jpg");
            img2.setMedia("https://charliebot.s3.eu-central-1.amazonaws.com/pricing2.jpg");
            img3.setMedia("https://charliebot.s3.eu-central-1.amazonaws.com/pricing3.jpg");
            img4.setMedia("https://charliebot.s3.eu-central-1.amazonaws.com/pricing4.jpg");
            img5.setMedia("https://charliebot.s3.eu-central-1.amazonaws.com/pricing5.jpg");

        inputMediaPhotos.add(img1);
        inputMediaPhotos.add(img2);
        inputMediaPhotos.add(img3);
        inputMediaPhotos.add(img4);
        inputMediaPhotos.add(img5);

        sendMediaGroup.setChatId(chatId);
        sendMediaGroup.setMedia(inputMediaPhotos);
        try {
            charlieWebhookBot.execute(sendMediaGroup);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
