package com.example.demo.controller;

import com.example.demo.config.resTemplate.ResponseException;
import com.example.demo.config.resTemplate.ResponseTemplate;
import com.example.demo.controller.dto.StudyReq;
import com.example.demo.controller.dto.StudyRes;
import com.example.demo.domain.Study;
import com.example.demo.service.StudyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/study")
@RequiredArgsConstructor
public class StudyController {

        @Autowired
        private final StudyService studyService;

        // 스터디 생성
        @ResponseBody
        @PostMapping("/create")
        @Operation(summary = "스터디 생성 API", description = "새로운 스터디를 생성합니다.")
        public ResponseTemplate<String> createStudy(@RequestBody StudyReq studyReq) {
            try {
                String name = studyService.createStudy(studyReq);
                return new ResponseTemplate<>(name);
            } catch (ResponseException e){
                return new ResponseTemplate<>((e.getStatus()));
            }
        }

        @ResponseBody
        @GetMapping("/study")
        @Operation(summary = "스터디 참가 조정 페이지에서 스터디를 가져오는 API")
        public ResponseTemplate<List<StudyRes>> getStudyList(@RequestParam("semesterId") Long semesterId){
            try{
                List<StudyRes> studyList = studyService.getStudyList(semesterId);

                return new ResponseTemplate<List<StudyRes>>(studyList);
            } catch (ResponseException e){
                return new ResponseTemplate<>((e.getStatus()));
            }
        }

}
