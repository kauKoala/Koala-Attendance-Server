package com.example.demo.service;

import com.example.demo.config.resTemplate.ResponseException;
import com.example.demo.controller.dto.StudentReq;
import com.example.demo.controller.dto.StudentRes;
import com.example.demo.domain.Student;
import com.example.demo.domain.Student_Study;
import com.example.demo.domain.Study;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.StudentStudyRepository;
import com.example.demo.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.config.resTemplate.ResponseTemplateStatus.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;
    private final StudentStudyRepository studentStudyRepository;
    private final StudyRepository studyRepository;

    @Transactional
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
        ArrayList<StudentRes> studentResList = new ArrayList<>();
        for (Student student : studentList){
            StudentRes studentRes = new StudentRes(student.getId(), student.getName() );
            studentResList.add(studentRes);
        }
        return studentResList;
    }
    @Transactional
    public void removeStudyFromStudyList(List<Long> studentIdList, Long studyId) throws ResponseException {
        for (Long studentId : studentIdList) {
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new ResponseException(STUDENT_NOT_FOUND));
            List<Student_Study> studentStudies = studentStudyRepository.findStudentStudiesByStudentId(student.getId());
            for (Student_Study studentStudy : studentStudies) {
                studentStudyRepository.delete(studentStudy);
            }
        }
    }
    @Transactional
    public void addStudyToStudyList(List<Long> studentIdList, Long studyId) throws ResponseException {
        for (Long studentId : studentIdList) {
            List<Student_Study> studentStudies = studentStudyRepository.findStudentStudiesByStudentId(studentId);
            //이미 해당 StudyId가 포함되어있는지 확인
            boolean isStudyLinked = studentStudies.stream()
                    .anyMatch(ss -> ss.getStudy().getId().equals(studyId));

            if (!isStudyLinked) {
                Student student = studentRepository.findById(studentId)
                        .orElseThrow(() -> new ResponseException(STUDENT_NOT_FOUND));

                Study study = studyRepository.findById(studyId)
                        .orElseThrow(() -> new ResponseException(STUDY_NOT_FOUND));

                Student_Study newStudentStudy = new Student_Study();
                newStudentStudy.setStudent(student);
                newStudentStudy.setStudy(study);

                studentStudyRepository.save(newStudentStudy);
            }
        }
    }


    public List<StudentRes> getStudentListByStudyId(Long studyId) throws ResponseException{
        List<Student> studentList = studentRepository.findByStudiesStudyId(studyId);
        List<StudentRes> studentResList = new ArrayList<>();
        for (Student student: studentList){
            studentResList.add(new StudentRes(student.getId(), student.getName()));
        }
        return studentResList;
    }

    public Student getStudentById(Long studentId) throws ResponseException{
        Optional<Student> student = studentRepository.findById(studentId);
        return student.orElseThrow(() -> new ResponseException(STUDENT_NOT_FOUND));
    }

    public Student getStudentByTistoryName(String studentName) throws ResponseException{
        Optional<Student> student = studentRepository.findByTistoryName(studentName);
        return student.orElseThrow(() -> new ResponseException(STUDENT_NOT_FOUND));
    }
}
