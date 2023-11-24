package com.example.demo.service;

import com.example.demo.config.resTemplate.ResponseException;
import com.example.demo.controller.dto.StudentReq;
import com.example.demo.domain.Student;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.demo.config.resTemplate.ResponseTemplateStatus.DUPLICATE_STUDENT;

@Service
@RequiredArgsConstructor
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    public String createStudent(StudentReq studentReq) throws ResponseException {

        Optional<Student> student = studentRepository.findByBaekjoonName(studentReq.getBaekjoonName());
        if (student.isEmpty()) {
            Student newStudent = Student.builder()
                    .studentReq(studentReq)
                    .build();
            studentRepository.save(newStudent);
            return newStudent.getName();
        } else {
            throw new ResponseException(DUPLICATE_STUDENT);
        }

    }
}
