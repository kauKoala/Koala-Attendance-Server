package com.example.demo.repository;

import com.example.demo.domain.Week;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;


public interface WeekRepository extends JpaRepository<Week, Long>{

    @Query("SELECT w FROM Week w JOIN w.study s WHERE w.startDate = :startDate AND s.id = :studyId")
    Optional<Week> findByStartDateAndStudyId(@Param("startDate") LocalDate startDate, @Param("studyId") Long studyId);


}