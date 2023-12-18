package com.example.demo.service;

import com.example.demo.controller.dto.HistoriesRes;
import com.example.demo.controller.dto.StudentRes;
import com.example.demo.domain.History;
import com.example.demo.config.resTemplate.ResponseException;
import com.example.demo.repository.HistoryRepository;
import com.example.demo.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<HistoriesRes> getHistoryList(Long studyId) throws ResponseException {
        //그 스터디에 해당하는 주 가져온다.
        int studyCount = studyRepository.countWeeksByStudyId(studyId);
        List<HistoriesRes> historyResList = new ArrayList<>();

        //그 스터디에 참여하는 학생들 가져온다.
        List<StudentRes> studentResList = studentService.getStudentListByStudyId(studyId);

        //for문으로 주별, 학생별 history를 돌린다.
        for (StudentRes studentRes : studentResList) {
            for (int week = 0; week < studyCount; week++) { //이후 크롤링은 두번째 주차부터 돌린다.
                Optional<History> history = historyRepository.findAllByStudyIdAndWeekIdAndStudentId(studyId, week, studentRes.getId());
                System.out.println("here, "+ week);
                System.out.println(history);
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

