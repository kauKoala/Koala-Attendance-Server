package com.example.demo.repository;

import com.example.demo.domain.Week;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;


public interface WeekRepository extends JpaRepository<Week, Long>{

    Optional<Week> findByStartDate(LocalDate startDate);

}