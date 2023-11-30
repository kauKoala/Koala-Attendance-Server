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

    @ResponseBody
    @GetMapping("/count")
    @Operation(summary="기수 갯수 반환 API", description = "마지막 기수를 반환하여 총 기수에 대해 조회합니다.")
    public ResponseTemplate<Long> countTotalSemesters() {
            Long totalSemester = semesterService.getTotalSemesterCount();
            return new ResponseTemplate<>(totalSemester);
    }
}
