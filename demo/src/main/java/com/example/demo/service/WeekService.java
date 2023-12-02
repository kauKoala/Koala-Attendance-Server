package com.example.demo.service;


import com.example.demo.config.resTemplate.ResponseException;
import com.example.demo.controller.dto.WeekReq;
import com.example.demo.domain.Week;
import com.example.demo.repository.WeekRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.demo.config.resTemplate.ResponseTemplateStatus.DUPLICATE_WEEK;

@Service
@RequiredArgsConstructor
public class WeekService {

    @Autowired
    private final WeekRepository weekRepository;

    @Operation(summary = "Week 생성 API", description = "학기 별 Week 를 생성합니다.")
    public String createWeek(WeekReq weekReq) throws ResponseException{
        Optional<Week> week = weekRepository.findByWeekNumber(weekReq.getWeekNumber());
        if (week.isEmpty()) {
            Week newWeek = Week.builder()
                    .weekReq(weekReq)
                    .build();
            weekRepository.save(newWeek);
            return String.valueOf(newWeek.getWeekNumber());
        } else {
            throw new ResponseException(DUPLICATE_WEEK);
        }

    }
}
