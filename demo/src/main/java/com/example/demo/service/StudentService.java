package com.example.demo.service;

import com.example.demo.config.resTemplate.ResponseException;
import com.example.demo.controller.dto.StudentReq;
import com.example.demo.controller.dto.StudentRes;
import com.example.demo.domain.Student;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.config.resTemplate.ResponseTemplateStatus.DUPLICATE_STUDENT;

@Service
@RequiredArgsConstructor
@Slf4j
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

    public ArrayList<StudentRes> getStudentList() throws ResponseException {
        List<Student> studentList = studentRepository.findAll();
        System.out.println("student"+(studentList));
        ArrayList<StudentRes> studentResList = new ArrayList<>();
        for (Student student : studentList){
            StudentRes studentRes = new StudentRes(student.getName());
            studentResList.add(studentRes);
        }
        return studentResList;
    }
}
