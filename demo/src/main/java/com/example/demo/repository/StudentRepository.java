package com.example.demo.repository;

import com.example.demo.domain.Student;
import com.example.demo.domain.Study;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByBaekjoonName(String baekjoonName);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Study WHERE id = :studyId", nativeQuery = true)
    void deleteStudyByStudyId(@Param("studyId") Long studyId);

}
