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
    private LocalDateTime startDate; // 시작 날짜
}
