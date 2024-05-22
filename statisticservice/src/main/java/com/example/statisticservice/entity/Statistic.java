package com.example.statisticservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "statistic")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "message")
    private String message;

    @Column(name = "create_date")
    private Date createDate;
}
