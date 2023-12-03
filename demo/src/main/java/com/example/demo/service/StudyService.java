package com.example.demo.service;

import com.example.demo.config.resTemplate.ResponseException;
import com.example.demo.controller.dto.StudyReq;
import com.example.demo.domain.Study;
import com.example.demo.domain.Week;
import com.example.demo.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.demo.config.resTemplate.ResponseTemplateStatus.DUPLICATE_STUDY;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudyService {

    @Autowired
    private final StudyRepository studyRepository;
    private final WeekService weekService;

    public String createStudy(StudyReq studyReq) throws ResponseException {

        Optional<Study> study = studyRepository.findByName(studyReq.getName());
        System.out.println(studyReq.getStudyWeeks());
        if (study.isEmpty()) {
            List<String> stringDateList = studyReq.getStudyWeeks(); // 클라이언트에서 받은 문자열 리스트

            List<Week> weekList2 = weekService.createWeekList(stringDateList);
            log.info(weekList2.toString());
            Study newStudy = new Study(studyReq.getName(), studyReq.getDescription(), weekList2);

            studyRepository.save(newStudy);
            return newStudy.getName();
        } else {
            throw new ResponseException(DUPLICATE_STUDY);
        }

    }
}
