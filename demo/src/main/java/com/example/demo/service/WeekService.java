package com.example.demo.service;


import com.example.demo.config.resTemplate.ResponseException;
import com.example.demo.controller.dto.WeekReq;
import com.example.demo.domain.Week;
import com.example.demo.repository.WeekRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.demo.config.resTemplate.ResponseTemplateStatus.DUPLICATE_WEEK;

@Service
@RequiredArgsConstructor
public class WeekService {

    @Autowired
    private final WeekRepository weekRepository;

    @Operation(summary = "Week 생성 API", description = "학기 별 Week 를 생성합니다.")
    public List<Week> createWeekList(List<String> stringDateList) throws ResponseException{
        List<LocalDate> weekList = stringDateList.stream()
                .map(LocalDate::parse)
                .collect(Collectors.toList());
        List<Week> weekList2 = new ArrayList<>();
        for (int i = 1; i <= weekList.size(); i++){
            LocalDate element = weekList.get(i-1);
            Optional<Week> week = weekRepository.findByStartDate(element);
            if (week.isEmpty()){
                Week newWeek = new Week(i, element);

                weekRepository.save(newWeek);
                weekList2.add(newWeek);
            } else {
                throw new ResponseException(DUPLICATE_WEEK);
            }
        }
        return weekList2;
    }
}
