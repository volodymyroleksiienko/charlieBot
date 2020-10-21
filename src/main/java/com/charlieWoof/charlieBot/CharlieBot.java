package com.charlieWoof.charlieBot;

import com.charlieWoof.charlieBot.data.service.AmazonClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CharlieBot extends TelegramLongPollingBot {
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if(message!=null &&message.hasText()){
            if (message.getText().equals("Сайт")){
                sendMsgWithInlineButton(message,"Тут ти можеш переглянути наш сайт","Відкрити сайт","https://charliewoof.com");
            }else  if (message.getText().equals("Запис на стрижку")){
                sendMsgWithInlineButton(message,"Щоб записатися перейдіть за посиланням","Записатися","https://n405948.yclients.com/company:147143");
            }else  if (message.getText().equals("Послуги та ціни")){
                List<String> imgList = new ArrayList<>();
                String separator = System.getProperty("file.separator");
                String path = separator + "src" + separator + "main" + separator + "resources"
                        + separator + "static" + separator + "img" + separator + "pricingImg" + separator;
                imgList.add(path+"pricing1.jpg");
                imgList.add(path+"pricing2.jpg");
                imgList.add(path+"pricing3.jpg");
                imgList.add(path+"pricing4.jpg");
                imgList.add(path+"pricing5.jpg");
                sendMsgWithImg(message,imgList);
            }else  if (message.getText().equals("Курси грумінгу")){
                sendMsgWithInlineButton(message,"Інформація про курси","Курси грумінгу","https://www.charliewoof.com/kursi-grumingu");
            }else if (message.getText().equals("Товари")){
                sendMsg(message,"");
            }else {
                sendMsg(message,"Скористайтеся кнопками під полем вводу");
            }
        }
    }

    private void sendMsg(Message message, String start) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(start);
        try{
            setButton(sendMessage);
            execute(sendMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void sendMsgWithInlineButton(Message message, String start,String nameBtn, String url) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(start);
        try{
            setButton(sendMessage);
            setInlineBtn(sendMessage,nameBtn,url);
            execute(sendMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void sendMsgWithImg(Message message, List<String> imgList) throws IOException {
        for(String img: imgList) {
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(message.getChatId().toString());
            String workingDir = System.getProperty("user.dir");
            File file = new File(workingDir + img);
            sendPhoto.setPhoto(file);
            try {
                execute(sendPhoto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setButton(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
        KeyboardRow  keyboardRow1 =  new KeyboardRow();
        keyboardRow1.add(new KeyboardButton().setText("Сайт"));
        keyboardRow1.add(new KeyboardButton().setText("Запис на стрижку"));
        keyboard.add(keyboardRow1);

        KeyboardRow  keyboardRow2 =  new KeyboardRow();
        keyboardRow2.add(new KeyboardButton().setText("Послуги та ціни"));
        keyboardRow2.add(new KeyboardButton().setText("Курси грумінгу"));
        keyboard.add(keyboardRow2);

//        KeyboardRow  keyboardRow3 =  new KeyboardRow();
//        keyboardRow3.add(new KeyboardButton().setText("Товари"));
//        keyboard.add(keyboardRow3);

        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    public void setInlineBtn(SendMessage sendMessage,String nameBtn, String url){
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List < List <InlineKeyboardButton>> rowsInline = new ArrayList<List<InlineKeyboardButton>>();
        List < InlineKeyboardButton > rowInline = new ArrayList <InlineKeyboardButton> ();
        rowInline.add(new InlineKeyboardButton().setText(nameBtn).setUrl(url));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        sendMessage.setReplyMarkup(markupInline);
    }

    public String getBotUsername() {
        return "CharlieWoofBot";
    }

    public String getBotToken() {
//        return "1353286018:AAFI6X0LaqDuWUxAHHs2jX6RytUYqGzC2do";
        return "1385530712:AAEoHrYRzn52DlfS1zp47mZFRlYCNte-wKU";
    }
}
