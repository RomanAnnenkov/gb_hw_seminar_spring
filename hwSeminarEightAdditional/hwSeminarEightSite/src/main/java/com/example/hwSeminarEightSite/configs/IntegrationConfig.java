package com.example.hwSeminarEightSite.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class IntegrationConfig {

    @Bean
    public MessageChannel inputMessageToLogChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel outputFileLogChannel() {
        return new DirectChannel();
    }

    @Bean
    @Transformer(inputChannel = "inputMessageToLogChannel", outputChannel = "outputFileLogChannel")
    public GenericTransformer<String, String> addDateTimeTransformer() {
        return textMessage -> {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            return currentDateTime.format(formatter) + " " + textMessage;
        };
    }

    @Bean
    @ServiceActivator(inputChannel = "outputFileLogChannel")
    public FileWritingMessageHandler messageHandler() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("logs"));
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);

        return handler;
    }
}
