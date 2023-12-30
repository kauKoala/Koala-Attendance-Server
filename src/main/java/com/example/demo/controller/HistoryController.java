package com.example.demo.controller;

import com.example.demo.config.resTemplate.ResponseException;
import com.example.demo.config.resTemplate.ResponseTemplate;
import com.example.demo.controller.dto.HistoriesRes;
import com.example.demo.service.HistoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @ResponseBody
    @GetMapping("/historyList")
    @Operation(summary = "히스토리 반환 API", description = "스터디에 맞는 히스토리를 추적합니다.")
    public ResponseTemplate<List<HistoriesRes>> getHistoryList(Long studyId) throws ResponseException {
        List<HistoriesRes> historyResList = historyService.getHistoryList(studyId);
        return new ResponseTemplate<>(historyResList);
    }

}
