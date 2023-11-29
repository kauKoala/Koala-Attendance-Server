package com.example.demo.service;

import com.example.demo.config.resTemplate.ResponseException;
import com.example.demo.controller.dto.StudyReq;
import com.example.demo.domain.Study;
import com.example.demo.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.demo.config.resTemplate.ResponseTemplateStatus.DUPLICATE_STUDY;

@Service
@RequiredArgsConstructor
public class StudyService {

    @Autowired
    private final StudyRepository studyRepository;

    public String createStudy(StudyReq studyReq) throws ResponseException {

        Optional<Study> study = studyRepository.findByNameAndSemesterId(studyReq.getName(), studyReq.getSemesterId());
        if (study.isEmpty()) {
            Study newStudy = Study.builder()
                    .studyReq(studyReq)
                    .build();
            studyRepository.save(newStudy);
            return newStudy.getName();
        } else {
            throw new ResponseException(DUPLICATE_STUDY);
        }

    }
}
