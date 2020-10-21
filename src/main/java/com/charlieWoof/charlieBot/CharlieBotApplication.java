package com.charlieWoof.charlieBot;

import com.charlieWoof.charlieBot.data.service.AmazonClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class CharlieBotApplication {

	public static void main(String[] args) {
//		ApiContextInitializer.init();
//		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
//		try {
//			telegramBotsApi.registerBot(new CharlieBot());
//		}catch (TelegramApiException e){
//			e.printStackTrace();
//		}
		SpringApplication.run(CharlieBotApplication.class, args);
	}

}
