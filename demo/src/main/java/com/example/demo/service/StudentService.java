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
            StudentRes studentRes = new StudentRes(student.getId(), student.getName() );
            studentResList.add(studentRes);
        }
        return studentResList;
    }

    public void removeStudyFromStudyList(List<Long> studentIdList, Long studyId) throws ResponseException{
        // 일단 그 스터디가 student DB에 연동이 되어있는지를 본다.
        // 연동 안되어있으면 delete 할 필요 없음
        studentRepository.deleteStudyByStudyId(studyId);

    }

    public void addStudyToStudyList(List<Long> studentIdList, Long studyId) throws ResponseException{
        //이미 추가가 되어있는지 본다.
        //추가 안 되어있으면 추가
    }

    public List<StudentRes> getStudentListByStudyId(Long studyId) throws ResponseException{
        List<Student> studentList = studentRepository.findByStudyId(studyId);
        List<StudentRes> studentResList = new ArrayList<>();
        for (Student student: studentList){
            studentResList.add(new StudentRes(student.getId(), student.getName()));
        }
        System.out.println(studentList);
        return studentResList;
    }

}
