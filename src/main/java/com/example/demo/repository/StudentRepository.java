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

    Optional<Student> findByTistoryName(String tistoryName);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Study WHERE id = :studyId", nativeQuery = true)
    void deleteStudyByStudyId(@Param("studyId") Long studyId);


    List<Student> findByStudiesStudyId(Long studyId);

    // 다음과 같이 연관 관계를 활용하여 해당 스터디와 연결된 학생을 찾는 메서드를 작성할 수 있습니다.
    @Query("SELECT s FROM Student s JOIN s.studies st WHERE st.id = :studyId")
    List<Student> findStudentsLinkedWithStudy(@Param("studyId") Long studyId);
}
