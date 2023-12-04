package com.example.demo.repository;

import com.example.demo.domain.Semester;
import com.example.demo.domain.SemesterType;
import com.example.demo.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SemesterRepository extends JpaRepository<Semester, Long> {

    Optional<Semester> findByYearAndSemesterType(int year, SemesterType semesterType);

    Long countBy();

    @Query("SELECT s.studyList FROM Semester s WHERE s.id = :semesterId")
    List<Study> findStudiesBySemesterId(Long semesterId);
}
