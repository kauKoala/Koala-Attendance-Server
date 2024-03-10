package com.example.demo.service;

import com.example.demo.controller.dto.*;
import com.example.demo.domain.*;
import com.example.demo.config.resTemplate.ResponseException;
import com.example.demo.repository.BaekjoonHistoryRepository;
import com.example.demo.repository.HistoryRepository;
import com.example.demo.repository.StudentStudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import static com.example.demo.config.resTemplate.ResponseTemplateStatus.BAEKJOON_HISTORY_NOT_FOUND;
import static com.example.demo.config.resTemplate.ResponseTemplateStatus.HISTORY_NOT_FOUND;
import static java.lang.Math.max;

@Service
@RequiredArgsConstructor
public class HistoryService {

    @Autowired
    private final HistoryRepository historyRepository;
    private final StudentService studentService;
    private final SemesterService semesterService;
    private final StudyService studyService;
    private final StudentStudyRepository studentStudyRepository;
    private final BaekjoonHistoryRepository baekjoonHistoryRepository;
    private final WeekService weekService;

    public List<CrawlingReq> getBaekjoonInfoForHistoryCrawling() throws ResponseException {
        List<CrawlingReq> crawlingReqList = new ArrayList<>();
        //현재 학기에 존재하는 studyList가져온다.
        Semester semester = semesterService.getCurrentSemester();
        List<StudyRes> studyResList = studyService.getStudyList(semester.getId());
        for (StudyRes studyRes: studyResList){
            //해당 studyList에 있는 week를 현재 시간 기준으로 가져온다.
            Long weekId = studyService.findWeekIdByTodayStartDate(studyRes.getId());
            List<Student_Study> student_studyList = studentStudyRepository.findStudent_StudiesByStudyId(studyRes.getId());
            for (Student_Study student_study: student_studyList){
                CrawlingReq crawlingReq = new CrawlingReq(student_study.getStudy().getId(),student_study.getStudent().getId(),student_study.getStudent().getName(),student_study.getStudent().getBaekjoonName(),weekId);
                crawlingReqList.add(crawlingReq);
            }
        }
        return crawlingReqList;
    }

    public void getBaekjoonCrawlingResult(List<CrawlingRes> crawlingResList) throws Exception {
        try{
            //history로 만들어 저장하는 작업
            for (CrawlingRes crawlingRes:crawlingResList) {
                Student student = studentService.getStudentById(crawlingRes.getStudentId());
                Study study = studyService.getStudyById(crawlingRes.getStudyId());
                Week week = weekService.getWeekById(crawlingRes.getWeekId());
                History history = historyRepository.findFirstByStudyIdAndWeekIdAndStudentId(study.getId(), week.getId(), student.getId())
                        .orElseGet(() -> new History(student, study, week));
                historyRepository.save(history);
                BaekjoonHistory baekjoonHistory = new BaekjoonHistory(history.getId(), crawlingRes.getSolvedBaekjoon());
                baekjoonHistoryRepository.save(baekjoonHistory);
            }
        } catch (Exception e){
            throw e;
        }
    }

    public void getTistoryCrawlingResult(List<CrawlingTistoryRes> crawlingTistoryResList) throws Exception {
        try{
            //history로 만들어 저장하는 작업
            for (CrawlingTistoryRes crawlingTistoryRes:crawlingTistoryResList) {

                Student student = studentService.getStudentByTistoryName(crawlingTistoryRes.getWriterName());
                Week week = weekService.getWeekById(crawlingTistoryRes.getWeekId());
                Study study = studyService.getStudyById(crawlingTistoryRes.getStudyId());

                //history를 먼저 찾고 없으면 생성하고, 있으면 해당 history의 tistory 갯수 업데이트
                History history = historyRepository
                        .findFirstByStudyIdAndWeekIdAndStudentId(study.getId(), week.getId(), student.getId())
                        .orElseGet(() -> {
                            History newHistory = new History(student, study, week);
                            newHistory.addTistoryWeek(1);
                            return historyRepository.save(newHistory);
                        });

                history.addTistoryWeek(1);
                historyRepository.save(history);
            }
        } catch (Exception e){
            throw e;
        }

    }

    public List<HistoriesRes> getHistoryList(Long studyId) throws ResponseException {
        List<HistoriesRes> historyResList = new ArrayList<>();
        List<StudentRes> studentResList = studentService.getStudentListByStudyId(studyId);
        List<Week> weekList = weekService.getWeekListByStudyId(studyId);
        System.out.println("weekList" + weekList.toString());

        for (StudentRes studentRes : studentResList) {
            for (int weekNum = 0; weekNum < weekList.size(); weekNum++) {
                int currentWeekNum = weekNum; // Lambda 표현식 내에서 사용되는 변수는 (사실상) final 이어야 함 -> 현재 for문에서 도는 weekNum
                getHistory(studyId, weekList.get(weekNum).getId(), studentRes.getId())
                    .ifPresent(history -> {
                                HistoriesRes historyListRes = new HistoriesRes(studentRes.getName(), weekList.get(currentWeekNum).getWeekNumber(),
                                        Math.max(0, history.getSolvedBaekjoonWeek()), history.getWrittenTistoryWeek());
                                historyResList.add(historyListRes);
                    });
            }
        }
        return historyResList;
    }

    public void saveHistoryList(Long studyId) throws ResponseException {
        List<StudentRes> studentResList = studentService.getStudentListByStudyId(studyId);
        List<Week> weekList = weekService.getWeekListByStudyId(studyId);
        System.out.println("weekList" + weekList.toString());

        for (StudentRes studentRes : studentResList) {
            for (int weekNum = 0; weekNum < weekList.size(); weekNum++) {
                int currentWeekNum = weekNum; // Lambda 표현식 내에서 사용되는 변수는 (사실상) final 이어야 함 -> 현재 for문에서 도는 weekNum
                getHistory(studyId, weekList.get(weekNum).getId(), studentRes.getId())
                        .ifPresent(history -> {
                            int solvedBaekjoonWeekDiff = compareWithPreviousHistory(studyId, history, currentWeekNum, studentRes.getId(), weekList);
                            history.setSolvedBaekjoonWeek(solvedBaekjoonWeekDiff);
                            historyRepository.save(history);
                        });
            }
        }
    }

    private int compareWithPreviousHistory(Long studyId, History history, int currentWeekNum, Long studentId, List<Week> weekList) {
        if (currentWeekNum > 0) {
            return getHistory(studyId, weekList.get(currentWeekNum - 1).getId(), studentId)
                .map(beforeHistory -> {
                    int beforeBaekjoonNum = baekjoonHistoryRepository.findById(beforeHistory.getId())
                        .map(beforeBaekjoonHistory -> beforeBaekjoonHistory.getSolvedBaekjoon().size()) //beforeBaekjoonHistory 객체들에 대해
                        .orElse(0);
                    int nowBaekjoonNum = baekjoonHistoryRepository.findById(history.getId())
                        .map(nowBaekjoonHistory -> nowBaekjoonHistory.getSolvedBaekjoon().size())
                        .orElse(0);
                    return nowBaekjoonNum - beforeBaekjoonNum;
                })
                .orElse(0);
        }
        return 0;
    }


    private Optional<History> getHistory(Long studyId, Long weekId, Long studentId) {
        return historyRepository.findFirstByStudyIdAndWeekIdAndStudentId(studyId, weekId, studentId);
    }
}

