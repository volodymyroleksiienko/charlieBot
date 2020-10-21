package com.charlieWoof.charlieBot.botConfig;

import com.charlieWoof.charlieBot.CharlieWebhookBot;
import com.charlieWoof.charlieBot.botapi.TelegramFacade;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;


@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class BotConfig {
    private String webHookPath;
    private String botUserName;
    private String botToken;

    @Bean
    public CharlieWebhookBot myWizardTelegramBot(TelegramFacade telegramFacade) {
        CharlieWebhookBot myBot = new CharlieWebhookBot(telegramFacade);
        myBot.setBotUserName(botUserName);
        myBot.setBotToken(botToken);
        myBot.setWebHookPath(webHookPath);

        return myBot;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
