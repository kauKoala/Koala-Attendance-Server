package com.example.demo.repository;

import com.example.demo.domain.Semester;
import com.example.demo.domain.SemesterType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SemesterRepository extends JpaRepository<Semester, Long> {

    Optional<Semester> findByYearAndSemesterType(int year, SemesterType semesterType);

    Long countBy();
}
