package com.example.demo.domain;

import com.example.demo.controller.dto.WeekReq;
import jakarta.persistence.Column;
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
    @Column(name="week_id")
    private Long id;
    private int weekNumber; //1주차, 2주차 ..
    private LocalDateTime startDate; // 시작 날짜
    private LocalDateTime endDate; // 종료 날짜

    @Builder
    public Week(WeekReq weekReq){
        this.weekNumber = weekReq.getWeekNumber();;
        this.startDate = weekReq.getStartDate();
        this.endDate = weekReq.getEndDate();
    }
}
