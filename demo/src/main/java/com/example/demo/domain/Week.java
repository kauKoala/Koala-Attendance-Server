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
    private Long semesterId; //1학기와 연동

    private LocalDateTime startDate; // 주의 시작 날짜
    private LocalDateTime endDate; // 주의 종료 날짜
}
