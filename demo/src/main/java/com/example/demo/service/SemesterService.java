package com.example.demo.service;

import com.example.demo.config.resTemplate.ResponseException;
import com.example.demo.controller.dto.SemesterReq;
import com.example.demo.domain.Semester;
import com.example.demo.repository.SemesterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.demo.config.resTemplate.ResponseTemplateStatus.*;

@Service
@RequiredArgsConstructor
public class SemesterService {

    @Autowired
    private final SemesterRepository semesterRepository;

    public String createSemester(SemesterReq semesterReq) throws ResponseException{
        Optional<Semester> semester = semesterRepository.findByYearAndSemesterType(semesterReq.getYear(), semesterReq.getSemesterType());
        if (semester.isEmpty()) {
            Semester newSemester = Semester.builder()
                    .semesterReq(semesterReq)
                    .build();
            semesterRepository.save(newSemester);
            return String.valueOf(newSemester.getYear())+newSemester.getSemesterType().toString()+"반환 성공";
        } else {
            throw new ResponseException(DUPLICATE_SEMESTER);
        }
    }

    public Long getTotalSemesterCount() {
        Long totalSemesterCount = Optional.ofNullable(semesterRepository.countBy())
                .orElse(0L);
        return totalSemesterCount;
    }
}
