package com.example.demo.service;

import com.example.demo.controller.dto.*;
import com.example.demo.domain.*;
import com.example.demo.config.resTemplate.ResponseException;
import com.example.demo.repository.BaekjoonHistoryRepository;
import com.example.demo.repository.HistoryRepository;
import com.example.demo.repository.StudentStudyRepository;
import com.example.demo.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import static com.example.demo.config.resTemplate.ResponseTemplateStatus.HISTORY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class HistoryService {

    @Autowired
    private final HistoryRepository historyRepository;
    private final StudyRepository studyRepository;
    private final StudentService studentService;
    private final SemesterService semesterService;
    private final StudyService studyService;
    private final StudentStudyRepository studentStudyRepository;
    private final BaekjoonHistoryRepository baekjoonHistoryRepository;
    private final WeekService weekService;

    public List<CrawlingReq> getInfoForHistoryCrawling() throws ResponseException {
        List<CrawlingReq> crawlingReqList = new ArrayList<>();
        //현재 학기에 존재하는 studyList가져온다.
        Semester semester = semesterService.getCurrentSemester();
        List<StudyRes> studyResList = studyService.getStudyList(semester.getId());
        for (StudyRes studyRes: studyResList){
            //해당 studyList에 있는 week를 현재 시간 기준으로 가져온다.
            Long weekId = studyService.findWeekIdByTodayStartDate(studyRes.getId());
            List<Student_Study> student_studyList = studentStudyRepository.findStudent_StudiesByStudyId(studyRes.getId());
            for (Student_Study student_study: student_studyList){
                CrawlingReq crawlingReq = new CrawlingReq(student_study.getStudy().getId(),student_study.getStudent().getId(),weekId);
                crawlingReqList.add(crawlingReq);
            }
        }
        return crawlingReqList;
    }

    public void getHistoryCrawlingResult(List<CrawlingRes> crawlingResList) throws Exception {
        try{
            //history로 만들어 저장하는 작업
            for (CrawlingRes crawlingRes:crawlingResList) {
                Student student = studentService.getStudentById(crawlingRes.getStudentId());
                Study study = studyService.getStudyById(crawlingRes.getStudyId());
                Week week = weekService.getWeekById(crawlingRes.getWeekId());

                History history = new History(student, study, week, crawlingRes.getWrittenTistoryWeek());
                historyRepository.save(history);
                BaekjoonHistory baekjoonHistory = new BaekjoonHistory(history.getId(), crawlingRes.getSolvedBaekjoon());
                baekjoonHistoryRepository.save(baekjoonHistory);
            }
        } catch (Exception e){
            throw e;
        }

    }

    public List<HistoriesRes> getHistoryList(Long studyId) throws ResponseException {
        //그 스터디에 해당하는 주 가져온다.
        int studyCount = studyRepository.countWeeksByStudyId(studyId);
        List<HistoriesRes> historyResList = new ArrayList<>();

        //그 스터디에 참여하는 학생들 가져온다.
        List<StudentRes> studentResList = studentService.getStudentListByStudyId(studyId);

        //for문으로 주별, 학생별 history를 돌린다.
        for (StudentRes studentRes : studentResList) {
            for (int week = 1; week <= studyCount; week++) { //이후 크롤링은 두번째 주차부터 돌린다.
                Optional<History> history = historyRepository.findAllByStudyIdAndWeekIdAndStudentId(studyId, week, studentRes.getId());
                if (history.isPresent()) {
                    boolean isSolved = checkProblemsSolvedThisWeek(history.get()); //이후 수정 필요
                    boolean isWritten = checkTistoryWrittenThisWeek(history.get());
                    HistoriesRes historyListRes = new HistoriesRes(studentRes.getName(), week, isSolved, isWritten);
                    historyResList.add(historyListRes);
                }
            }
        }
        return historyResList;
    }

    public boolean checkProblemsSolvedThisWeek (History history){

        int solvedProblemsThisWeek = history.getSolvedBaekjoonWeek();
        if (solvedProblemsThisWeek > 3) {
            return true;
        }
        return false;
    }


    public boolean checkTistoryWrittenThisWeek (History history){
        int writtenTistoryThisWeek = history.getWrittenTistoryWeek();

        if (writtenTistoryThisWeek > 0) {
            return true;
        }
        return false;
    }
}

