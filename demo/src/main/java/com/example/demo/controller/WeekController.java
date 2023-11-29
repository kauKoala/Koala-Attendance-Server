package com.example.demo.controller;


import com.example.demo.config.resTemplate.ResponseException;
import com.example.demo.config.resTemplate.ResponseTemplate;
import com.example.demo.controller.dto.WeekReq;
import com.example.demo.service.WeekService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/week")
@RequiredArgsConstructor
public class WeekController {


    @Autowired
    private final WeekService weekService;

    @ResponseBody
    @PostMapping("/create")
    @Operation(summary = "주 생성 API", description = "새로운 주를 생성하고 주의 시작과 끝을 표시합니다.")
    public ResponseTemplate<String> createWeek(@RequestBody WeekReq weekReq) {
        try {
            String what = weekService.createWeek(weekReq);
            return new ResponseTemplate<>(what);
        } catch (ResponseException e) {
            return new ResponseTemplate<>((e.getStatus()));
        }
    }
}
