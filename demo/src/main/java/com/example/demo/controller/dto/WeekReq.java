package com.example.demo.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class WeekReq {

    private int weekNumber; //1주차, 2주차 ..
    private Long semesterId; // 학기(1학기, 2학기, 여름방학...)와 연동
    private LocalDateTime startDate; // 시작 날짜
    private LocalDateTime endDate; // 종료 날짜
}
