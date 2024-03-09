package com.example.demo.service;


import com.example.demo.config.resTemplate.ResponseException;
import com.example.demo.domain.Student;
import com.example.demo.domain.Week;
import com.example.demo.repository.StudyRepository;
import com.example.demo.repository.WeekRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.demo.config.resTemplate.ResponseTemplateStatus.*;

@Service
@RequiredArgsConstructor
public class WeekService {

    @Autowired
    private final WeekRepository weekRepository;
    private final StudyRepository studyRepository;
    @Transactional
    @Operation(summary = "Week 생성 API", description = "학기 별 Week 를 생성합니다.")
    public List<Week> createWeekList(List<String> stringDateList) throws ResponseException{
        System.out.println(stringDateList);
        List<LocalDate> weekList = stringDateList.stream()
                .map(LocalDate::parse)
                .collect(Collectors.toList());
        List<Week> weekList2 = new ArrayList<>();
        for (int i = 1; i <= weekList.size(); i++){
            LocalDate element = weekList.get(i-1);

            Week newWeek = new Week(i, element);

            weekRepository.save(newWeek);
            weekList2.add(newWeek);
        }
        return weekList2;
    }

    public Week getWeekById(Long weekId) throws ResponseException{
        Optional<Week> week = weekRepository.findById(weekId);
        return week.orElseThrow(() -> new ResponseException(WEEK_NOT_FOUND));
    }

    public Long getWeekIdByStartDateAndStudyId(LocalDate date, Long studyId) throws ResponseException{

        Optional<Week> week = weekRepository.findByStartDateAndStudyId(date, studyId);
        return week.orElseThrow(() ->new ResponseException(WEEK_NOT_FOUND)).getId();
    }

    public List<Week> getWeekListByStudyId(Long studyId) {
        return studyRepository.findWeekByStudyIdOrderByWeekNumberAsc(studyId);
    }

    public int getWeekCountByStudyId(Long studyId){
        return studyRepository.findWeekByStudyIdOrderByWeekNumberAsc(studyId).size();
    }
}
