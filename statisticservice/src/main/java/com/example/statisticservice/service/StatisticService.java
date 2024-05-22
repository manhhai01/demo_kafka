package com.example.statisticservice.service;

import com.example.statisticservice.dto.StatisticDto;
import com.example.statisticservice.entity.Statistic;
import com.example.statisticservice.repository.StatisticRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class StatisticService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StatisticRepository statisticRepository;

    @KafkaListener(id = "statisticGroup", topics = "statistic")
    public void listen(Statistic statistic) {

        logger.info("Received: " + statistic.getMessage());
//        Statistic statistic = new Statistic();
//        statistic.setMessage(statisticDto.getMessage());
//        statistic.setCreateDate(statisticDto.getCreateDate());
        statisticRepository.save(statistic);
    }
}
