package com.example.demo.domain;

import com.example.demo.controller.dto.WeekReq;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Week {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="week_id")
    private Long id;
    private int weekNumber; //1주차, 2주차 ..
    private LocalDate startDate; // 시작 날짜
    private LocalDate endDate; // 종료 날짜

    @Builder
    public Week(int weekNumber, LocalDate startDate){
        this.weekNumber = weekNumber;
        this.startDate = startDate;
        this.endDate = this.startDate.plusDays(6);
    }
}
