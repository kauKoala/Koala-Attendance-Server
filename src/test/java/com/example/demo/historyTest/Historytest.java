package com.example.demo.historyTest;

import com.example.demo.config.resTemplate.ResponseException;
import com.example.demo.controller.dto.StudyRes;
import com.example.demo.domain.*;
import com.example.demo.repository.*;
import com.example.demo.service.SemesterService;
import com.example.demo.service.StudyService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

//@SpringBootTest
//public class Historytest {
//
//    @Autowired
//    private HistoryRepository historyRepository;
//    @Autowired
//    private SemesterService semesterService;
//    @Autowired
//    private StudyService studyService;
//    @Autowired
//    private StudentStudyRepository studentStudyRepository;
//
//    @Test
//    @Transactional
//    void history_DB에_주입() throws ResponseException {
//
//        Semester semester = semesterService.getCurrentSemester(); //현재 학기 가져온다.
//        //현재 학기에 존재하는 studyList가져온다.
//        List<StudyRes> studyResList = studyService.getStudyList(semester.getId());
//        System.out.println("studyResList"+studyResList);
//        for (StudyRes studyRes: studyResList){
//            System.out.println("studyRes"+studyRes.getName());
//
//            List<Student_Study> student_studyList = studentStudyRepository.findStudent_StudiesByStudyId(studyRes.getId());
//            for (Student_Study student_study: student_studyList){
//                String baekjoonName = student_study.getStudent().getBaekjoonName();
//                List<Integer> solvedBaekjoonProblems = Arrays.asList(1000, 1001, 1002, 1003);
//                // TODO
//                //해당 주를 찾아 넣는다.
//                History history = new History();
//                history.setStudent(student_study.getStudent());
//                System.out.println("백준이름"+student_study.getStudent().getBaekjoonName());
//                history.setStudy(student_study.getStudy());
//                history.setSolvedBaekjoon(solvedBaekjoonProblems);
//
//                historyRepository.save(history);
//                Long historyId = history.getId();
//                System.out.println("historyId:"+historyId);
//            }
//        }
//    }
//}
