package com.example.accountservice.controller;

import com.example.accountservice.dto.MessageDto;
import com.example.accountservice.dto.StatisticDto;
import com.example.accountservice.payload.request.AccountRequest;
import com.example.accountservice.payload.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping("")
    public ResponseEntity<?> createAccount(@RequestBody AccountRequest account) {
        StatisticDto statistic = new StatisticDto("Account " + account.getEmail() + " is created.", new Date());

        // send notification
        MessageDto message = new MessageDto();
        message.setTo(account.getEmail());
        message.setToName(account.getName());
        message.setSubject("Welcome!");
        message.setContent("This is a message.");

        kafkaTemplate.send("notification", message);
        kafkaTemplate.send("statistic", statistic);

        BaseResponse response = new BaseResponse();
        response.setStatus(200);
        response.setMessage("Success");
        response.setData(account);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
