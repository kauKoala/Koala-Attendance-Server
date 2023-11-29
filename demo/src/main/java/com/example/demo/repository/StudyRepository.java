package com.example.demo.repository;

import com.example.demo.domain.Student;
import com.example.demo.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyRepository extends JpaRepository<Study, Long> {

    Optional<Study> findByNameAndSemesterId(String name, int semesterId);

}
