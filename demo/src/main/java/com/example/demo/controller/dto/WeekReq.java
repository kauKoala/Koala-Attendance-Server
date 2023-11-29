package com.example.demo.controller.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class WeekReq {
    @Schema(description="해당 주차가 몇 주차인지")
    private int weekNumber; //1주차, 2주차 ..
    @Schema(description="어떤 학기 (1,2,여름,겨울) 와 mapping되어 있는가")
    private Long semesterId; // 학기(1학기, 2학기, 여름방학...)와 연동
    private LocalDateTime startDate; // 시작 날짜
    private LocalDateTime endDate; // 종료 날짜
}
