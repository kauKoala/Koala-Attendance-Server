package com.example.demo.service;

import com.example.demo.domain.History;
import com.example.demo.config.resTemplate.ResponseException;
import com.example.demo.controller.dto.HistoryRes;
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

    public List<HistoryRes> getHistoryList(Long studyId) throws ResponseException {
        //그 스터디에 해당하는 주를 가져오고
        int studyCount = studyRepository.countWeeksByStudyId(studyId);

        List<HistoryRes> historyResList = new ArrayList<>();
        //for문으로 week를 돌린다.
        for (int week = 1; week < studyCount; week++) { //두번째 주차부터 돌린다.
            Optional<History> history = historyRepository.findAllByStudyIdAndWeekId(studyId, studyCount);
            if (history.isEmpty()) {
                throw new ResponseException(HISTORY_NOT_FOUND);
            } else {
                System.out.println(history);
                boolean isSolved = checkProblemsSolvedThisWeek(history.get());
                boolean isWritten = checkTistoryWrittenThisWeek(history.get());
            }

        }
        return historyResList;
    }

    public boolean checkProblemsSolvedThisWeek (History history){

        int solvedProblemsThisWeek = history.getSolvedBaekjoonWeek();
        if (solvedProblemsThisWeek > 3){
            return true;
        }
        return false;
    }



    public boolean checkTistoryWrittenThisWeek (History history){
        int writtenTistoryThisWeek = history.getWrittenTistoryWeek();

        if(writtenTistoryThisWeek > 0){
            return true;
        }
        return false;
    }
}

