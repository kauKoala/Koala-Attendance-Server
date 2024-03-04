package com.example.demo.service;

import com.example.demo.config.resTemplate.ResponseException;
import com.example.demo.controller.dto.SemesterRes;
import com.example.demo.domain.Semester;
import com.example.demo.domain.SemesterType;
import com.example.demo.domain.Study;
import com.example.demo.repository.SemesterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.config.resTemplate.ResponseTemplateStatus.*;

@Service
public class SemesterService {

    private final SemesterRepository semesterRepository;


    public SemesterService(SemesterRepository semesterRepository){
        this.semesterRepository = semesterRepository;
    }
    public Long getTotalSemesterCount() {
        Long totalSemesterCount = Optional.ofNullable(semesterRepository.countBy())
                .orElse(0L);
        return totalSemesterCount;
    }

    public Semester getCurrentSemester() {
        String currentYear = getCurrentYear();
        SemesterType currentSemesterType = getCurrentSemesterType();
        Optional<Semester> currentSemesterOptional = semesterRepository.findByYearAndSemesterType(currentYear, currentSemesterType);

        return currentSemesterOptional.orElseGet(() -> new Semester(currentYear, currentSemesterType));
    }

    public String getCurrentYear() {
        return String.valueOf(LocalDate.now().getYear());
    }

    public SemesterType getCurrentSemesterType() {
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentDay = currentDate.getDayOfMonth();

        if ((currentMonth == 3 && currentDay >= 6) || (currentMonth == 4 || currentMonth == 5) || (currentMonth == 6 && currentDay <= 20)) {
            return SemesterType.FIRST_SEMESTER;  //3.6 ~ 6.20
        } else if ((currentMonth == 6 && currentDay >= 21) || (currentMonth == 7 || currentMonth == 8) || (currentMonth == 9 && currentDay <= 1)) {
            return SemesterType.SUMMER_VACATION;  //6.20 ~ 9.1
        } else if ((currentMonth == 9 && currentDay >= 2) || (currentMonth == 10 || currentMonth == 11) || (currentMonth == 12 && currentDay <= 20)) {
            return SemesterType.SECOND_SEMESTER;  //9.1 ~ 12.20
        } else {
            return SemesterType.WINTER_VACATION;  //12.20 ~ 2.28
        }
    }

    @Transactional
    public Long addStudy(Study study) {
        Semester semester = getCurrentSemester();
        semester.addStudy(study);
        semesterRepository.save(semester);
        return semester.getId();
    }

    public List<SemesterRes> getAllSemester(){
        List<Semester> semesterList = semesterRepository.findAll();
        List<SemesterRes> semesterNameList = new ArrayList<>();

        for (Semester semester: semesterList){
            SemesterRes semesterRes = new SemesterRes(semester.getId(), semester.getYear(), semester.getSemesterType());
            semesterNameList.add(semesterRes);
        }
        return semesterNameList;
    }

    public Semester findByYearAndSemesterType(String year, SemesterType semesterType) throws ResponseException {
        Optional<Semester> semester = semesterRepository.findByYearAndSemesterType(year, semesterType);

        return semester.orElseThrow(() -> new ResponseException(SEMESTER_NOT_FOUND));
    }


}
