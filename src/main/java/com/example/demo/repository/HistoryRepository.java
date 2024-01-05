package com.example.demo.repository;

import com.example.demo.domain.History;
import com.example.demo.domain.Week;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    /* TODO
    * semesterId 와 Join 해서 가져오는 로직 필요
    * 어떤 semesterId에 있는 study들이 있음
    * 그 스터디에 대한 정보 가져옴
    * 그리고 그 스터디에 참여하고 있는 학생들 가져옴
    * -> 이후 출석부에 입력
    * */

    Optional<History> findAllByStudyIdAndWeekIdAndStudentId(Long studyId, int weekId, Long studentId);


}
