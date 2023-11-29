package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Week {
    @Id
    @GeneratedValue
    private Long id;
    private int weekNumber; //1주차, 2주차 ..
    private Long semesterId; //학기(1학기, 2학기, 여름방학...)와 연동

    private LocalDateTime startDate; // 시작 날짜
    private LocalDateTime endDate; // 종료 날짜
}
