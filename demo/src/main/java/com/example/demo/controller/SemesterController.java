package com.example.demo.controller;


import com.example.demo.config.resTemplate.ResponseException;
import com.example.demo.config.resTemplate.ResponseTemplate;
import com.example.demo.controller.dto.SemesterReq;
import com.example.demo.service.SemesterService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/semester")
@RequiredArgsConstructor
public class SemesterController {

    @Autowired
    private final SemesterService semesterService;

    @ResponseBody
    @PostMapping("/create")
    @Operation(summary = "학기 생성 API", description = "새로운 기수를 등록합니다.")
    public ResponseTemplate<String> createSemester(@RequestBody SemesterReq semesterReq){
        try {
            String name = semesterService.createSemester(semesterReq);
            return new ResponseTemplate<>(name);
        } catch (ResponseException e) {
            return new ResponseTemplate<>((e.getStatus()));
        }
    }
}
