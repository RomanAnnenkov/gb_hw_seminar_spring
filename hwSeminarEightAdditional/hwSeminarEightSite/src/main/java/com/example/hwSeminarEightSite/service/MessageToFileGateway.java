package com.example.hwSeminarEightSite.service;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(defaultRequestChannel = "inputMessageToLogChannel")
public interface MessageToFileGateway {
    void writeToFile(@Header(FileHeaders.FILENAME) String filename, String message);
}
