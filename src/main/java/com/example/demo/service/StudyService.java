package com.example.demo.service;

import com.example.demo.config.resTemplate.ResponseException;
import com.example.demo.controller.dto.StudyReq;
import com.example.demo.controller.dto.StudyRes;
import com.example.demo.domain.Study;
import com.example.demo.domain.Week;
import com.example.demo.repository.SemesterRepository;
import com.example.demo.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.config.resTemplate.ResponseTemplateStatus.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudyService {

    @Autowired
    private final StudyRepository studyRepository;
    private final SemesterRepository semesterRepository;
    private final SemesterService semesterService;
    private final WeekService weekService;

    @Transactional
    public String createStudy(StudyReq studyReq) throws ResponseException {

        Optional<Study> study = studyRepository.findByName(studyReq.getName());
        if (study.isEmpty()) {
            List<String> stringDateList = studyReq.getStudyWeeks(); // 클라이언트에서 받은 문자열 리스트

            List<Week> weekList2 = weekService.createWeekList(stringDateList);
            log.info(weekList2.toString());
            Study newStudy = new Study(studyReq.getName(), studyReq.getDescription(), weekList2);

            studyRepository.save(newStudy);

            //해당 학기에 studyList 안에 study 추가
            semesterService.addStudy(newStudy);

            return newStudy.getName();
        } else {
            throw new ResponseException(DUPLICATE_STUDY);
        }

    }

    public List<StudyRes> getStudyList(Long semesterId) throws ResponseException {
        List<Study> studyList = semesterRepository.findStudiesBySemesterId(semesterId);
        List<StudyRes> studyResList = new ArrayList<>();
        if (!studyList.isEmpty()) {
            for(Study study: studyList){
                StudyRes studyRes = new StudyRes(study.getId(), study.getName(), study.getWeeks().size());
                studyResList.add(studyRes);
            }
            return studyResList;
        } else {
            throw new ResponseException(STUDY_NOT_FOUND);
        }
    }

    public Long findWeekIdByTodayStartDate(Long studyId) throws ResponseException {
        Study study = studyRepository.findById(studyId)
                .orElseThrow(() -> new ResponseException(STUDY_NOT_FOUND));

        LocalDate today = LocalDate.now();
        Optional<Long> weekId = study.getWeeks().stream()
                .filter(week -> week.getStartDate().equals(today))
                .map(Week::getId)
                .findFirst();

        return weekId.orElseThrow(() -> new ResponseException(WEEK_NOT_FOUND)); // 찾은 weekId가 없을 경우 null 반환
    }

    public Study getStudyById(Long studyId) throws ResponseException{
        Optional<Study> study = studyRepository.findById(studyId);
        return study.orElseThrow(() -> new ResponseException(STUDY_NOT_FOUND));
    }

}
