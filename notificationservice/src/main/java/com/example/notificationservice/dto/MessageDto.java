package com.example.notificationservice.dto;

import lombok.Data;

@Data
public class MessageDto {
    private String to;
    private String toName;
    private String subject;
    private String content;
}
