package com.example.demo.repository;

import com.example.demo.domain.Student;
import com.example.demo.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudyRepository extends JpaRepository<Study, Long> {

    Optional<Study> findByName(String name);

    @Query("SELECT COUNT(s) FROM Study s JOIN s.weeks w WHERE s.id = :studyId")
    int countWeeksByStudyId(@Param("studyId") Long studyId);


}
