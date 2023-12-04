package com.example.demo.service;

import com.example.demo.config.resTemplate.ResponseException;
import com.example.demo.controller.dto.SemesterRes;
import com.example.demo.domain.Semester;
import com.example.demo.domain.SemesterType;
import com.example.demo.repository.SemesterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

import static com.example.demo.config.resTemplate.ResponseTemplateStatus.*;

@Service
@RequiredArgsConstructor
public class SemesterService {

    @Autowired
    private final SemesterRepository semesterRepository;


    public Long getTotalSemesterCount() {
        Long totalSemesterCount = Optional.ofNullable(semesterRepository.countBy())
                .orElse(0L);
        return totalSemesterCount;
    }

    public String getCurrentYear() {
        return String.valueOf(LocalDate.now().getYear());
    }

    public SemesterType getCurrentSemester() {
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentDay = currentDate.getDayOfMonth();

        if ((currentMonth == 3 && currentDay >= 1) || (currentMonth == 4 || currentMonth == 5) || (currentMonth == 6 && currentDay <= 20)) {
            return SemesterType.FIRST_SEMESTER;  //3.1 ~ 6.20
        } else if ((currentMonth == 6 && currentDay >= 21) || (currentMonth == 7 || currentMonth == 8) || (currentMonth == 9 && currentDay <= 1)) {
            return SemesterType.SUMMER_VACATION;  //6.20 ~ 9.1
        } else if ((currentMonth == 9 && currentDay >= 2) || (currentMonth == 10 || currentMonth == 11) || (currentMonth == 12 && currentDay <= 20)) {
            return SemesterType.SECOND_SEMESTER;  //9.1 ~ 12.20
        } else {
            return SemesterType.WINTER_VACATION;  //12.20 ~ 2.28
        }
    }
}
