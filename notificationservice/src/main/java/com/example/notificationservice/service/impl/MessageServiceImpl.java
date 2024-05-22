package com.example.notificationservice.service.impl;

import com.example.notificationservice.dto.MessageDto;
import com.example.notificationservice.service.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IEmailService emailService;

    @KafkaListener(id = "notificationGroup", topics = "notification")
    public void listen(MessageDto message) {
        logger.info("Received: " + message.getTo());
        emailService.sendEmail(message);
    }
}
