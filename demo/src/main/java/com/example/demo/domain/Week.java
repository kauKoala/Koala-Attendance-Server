package com.example.demo.domain;

import com.example.demo.controller.dto.WeekReq;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Week {

    @Id
    @GeneratedValue
    private Long id;
    private int weekNumber; //1주차, 2주차 ..
    private Long semesterId; // //Join 필요 Semester과 연동 - Semester:Week 1:다
    private LocalDateTime startDate; // 시작 날짜
    private LocalDateTime endDate; // 종료 날짜

    @Builder
    public Week(WeekReq weekReq){
        this.weekNumber = weekReq.getWeekNumber();;
        this.semesterId = weekReq.getSemesterId();
        this.startDate = weekReq.getStartDate();
        this.endDate = weekReq.getEndDate();
    }
}
