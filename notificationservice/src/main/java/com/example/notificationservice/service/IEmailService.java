package com.example.notificationservice.service;

import com.example.notificationservice.dto.MessageDto;

public interface IEmailService {
    void sendEmail(MessageDto message);
}
