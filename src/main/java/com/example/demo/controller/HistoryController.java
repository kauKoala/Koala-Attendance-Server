package com.example.demo.controller;

import com.example.demo.config.resTemplate.ResponseException;
import com.example.demo.config.resTemplate.ResponseTemplate;
import com.example.demo.controller.dto.*;
import com.example.demo.service.HistoryService;
import com.example.demo.service.WeekService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_DATE;

@RestController
@RequestMapping("/api/v1/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;
    private final WeekService weekService;

    @ResponseBody
    @GetMapping("/historyList")
    @Operation(summary = "히스토리 반환 API", description = "스터디에 맞는 히스토리를 추적합니다.")
    public ResponseTemplate<List<HistoriesRes>> getHistoryList(Long studyId) throws ResponseException {
        List<HistoriesRes> historyResList = historyService.getHistoryList(studyId);
        return new ResponseTemplate<>(historyResList);
    }

    @ResponseBody
    @GetMapping("/crawlingBaekjoonReq")
    @Operation(summary = "백준 크롤링을 위한 요청 API")
    public ResponseTemplate<List<CrawlingReq>> getBaekjoonCrawlingReq() throws ResponseException {
        try {
            List<CrawlingReq> crawlingReqList = historyService.getBaekjoonInfoForHistoryCrawling();
            return new ResponseTemplate<>(crawlingReqList);
        } catch (ResponseException e){
            return new ResponseTemplate<>(e.getStatus());
        }
    }

    @ResponseBody
    @PostMapping("/crawlingBaekjoonRes")
    @Operation(summary = "백준 크롤링을 완료한 후 반환받는 API")
    public ResponseTemplate<String> getBaekjoonCrawlingRes(@RequestBody List<CrawlingRes> crawlingResList){
        try {
            historyService.getBaekjoonCrawlingResult(crawlingResList);
            return new ResponseTemplate<>("success");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ResponseBody
    @PostMapping("/crawlingTistoryReq")
    @Operation(summary = "티스토리 크롤링을 위한 요청 API")
    public ResponseTemplate<Long> getTistoryCrawlingReq(@RequestBody CrawlingTistoryReq crawlingTistoryReq) throws ResponseException {
        try {
            LocalDate nowDate = LocalDate.parse(crawlingTistoryReq.getDate(), ISO_DATE);

            Long weekId = weekService.getWeekIdByStartDateAndStudyId(nowDate, crawlingTistoryReq.getStudyId());
            return new ResponseTemplate<>(weekId);
        } catch (ResponseException e){
            return new ResponseTemplate<>(e.getStatus());
        }
    }

    @ResponseBody
    @PostMapping("/crawlingTistoryRes")
    @Operation(summary = "티스토리 크롤링을 완료한 후 반환받는 API")
    public ResponseTemplate<String> getTistoryCrawlingRes(@RequestBody List<CrawlingTistoryRes> crawlingResList){
        try {
            historyService.getTistoryCrawlingResult(crawlingResList);
            return new ResponseTemplate<>("success");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
