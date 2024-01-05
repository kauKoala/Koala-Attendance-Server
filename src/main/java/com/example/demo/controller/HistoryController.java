package com.example.demo.controller;

import com.example.demo.config.resTemplate.ResponseException;
import com.example.demo.config.resTemplate.ResponseTemplate;
import com.example.demo.controller.dto.CrawlingReq;
import com.example.demo.controller.dto.CrawlingRes;
import com.example.demo.controller.dto.HistoriesRes;
import com.example.demo.service.HistoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.web.bind.annotation.*;

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

    @ResponseBody
    @GetMapping("/crawlingReq")
    @Operation(summary = "크롤링 요청 API")
    public ResponseTemplate<List<CrawlingReq>> getCrawlingReq() throws ResponseException {
        try {
            List<CrawlingReq> crawlingReqList = historyService.getInfoForHistoryCrawling();
            return new ResponseTemplate<>(crawlingReqList);
        } catch (ResponseException e){
            return new ResponseTemplate<>(e.getStatus());
        }
    }

    @ResponseBody
    @PostMapping("/crawlingRes")
    @Operation(summary = "크롤링 요청 API")
    public ResponseTemplate<String> getCrawlingRes(@RequestBody List<CrawlingRes> crawlingResList){
        try {
            historyService.getHistoryCrawlingResult(crawlingResList);
            return new ResponseTemplate<>("success");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
