package com.example.notificationservice.service.impl;

import com.example.notificationservice.dto.MessageDto;
import com.example.notificationservice.service.IEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;

@Service
public class EmailServiceImpl implements IEmailService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender javaMailSender; // để gửi email

    @Autowired
    private SpringTemplateEngine springTemplateEngine; // đọc email template

    @Value("${spring.mail.username}")
    private String from;

    @Override
    @Async
    public void sendEmail(MessageDto message) {
        try {
            logger.info("START... Sending email");
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper (mimeMessage, StandardCharsets.UTF_8.name());
            // load template email with content
            Context context = new Context();
            context.setVariable("name", message.getToName());
            context.setVariable("content", message.getContent());
            String html = springTemplateEngine.process("welcome-email", context);

            // send email
            helper.setTo(message.getTo());
            helper.setText(html, true);
            helper.setSubject(message.getSubject());
            helper.setFrom(from);
            javaMailSender.send(mimeMessage);
            logger.info("END... Email sent success");
        } catch (MessagingException e) {
            logger.error("Email sent with error: " + e.getMessage());
        }
    }
}
