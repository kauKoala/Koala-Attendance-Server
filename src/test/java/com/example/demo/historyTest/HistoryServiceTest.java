package com.example.demo.historyTest;

import com.example.demo.controller.dto.CrawlingRes;
import com.example.demo.controller.dto.StudentReq;
import com.example.demo.domain.*;
import com.example.demo.repository.BaekjoonHistoryRepository;
import com.example.demo.repository.HistoryRepository;
import com.example.demo.repository.StudentStudyRepository;
import com.example.demo.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class HistoryServiceTest {


    @Mock
    private HistoryRepository historyRepository;
    @Mock
    private StudentService studentService;
    @Mock
    private SemesterService semesterService;
    @Mock
    private StudyService studyService;
    @Mock
    private StudentStudyRepository studentStudyRepository;
    @Mock
    private BaekjoonHistoryRepository baekjoonHistoryRepository;
    @Mock
    private WeekService weekService;
    @InjectMocks
    private HistoryService historyService;


    @BeforeEach
    void setUp(){

    }

    @DisplayName("history 저장")
    @Test
    @Transactional
    void history를_만들어_저장하려는데_이미_존재한다() throws Exception {

        Student student = Student.builder().studentReq(new StudentReq("전영서", "20wjsdudtj", "jeonyoungseo")).build();
        Study study = Study.builder().name("기초 코테").description("테스트를 위한 스터디명").studyWeeks(List.of("2024-01-23", "2024-01-24")).build();
        Week week = new Week(1, LocalDate.of(2024,1,23));
        History history = new History(student, study, week);
        historyRepository.save(history);
        BaekjoonHistory baekjoonHistory = new BaekjoonHistory(1L,List.of(2000,20001));
        assertNotNull(history);


//        // given
//        List<CrawlingRes> crawlingResList = new ArrayList<>();
//        CrawlingRes crawlingRes = new CrawlingRes();
//        crawlingRes.setStudyId(1L);
//        crawlingRes.setStudentId(1L);
//        crawlingRes.setWeekId(1L);
//        crawlingRes.setSolvedBaekjoon(Arrays.asList(1001, 1003, 1005));
//        crawlingResList.add(crawlingRes);
//
//        // when
//        Optional<History> foundHistory = historyRepository.findFirstByStudyIdAndWeekIdAndStudentId(1L, 1L, 1L);
//
//        // then
//        // historyService에서 history 객체를 찾아올 수 있도록 확인하는 예시
//
//        assertThat(foundHistory).isPresent();
    }
}
