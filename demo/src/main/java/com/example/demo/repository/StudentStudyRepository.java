package com.example.demo.repository;

import com.example.demo.domain.Student_Study;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentStudyRepository extends JpaRepository<Student_Study, Long> {

    List<Student_Study> findStudentStudiesByStudentId(Long studentId);

    List<Student_Study> findStudent_StudiesByStudyId(Long studyId);
}
