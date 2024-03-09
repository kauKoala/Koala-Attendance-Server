package com.example.demo.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter @Data
@AllArgsConstructor @NoArgsConstructor
public class StudyReq {
    @Schema(description="스터디 명")
    public String name;
    @Schema(description="스터디에 대한 간단한 소개")
    public String description;
    @Schema(description="시작하는 주차에 대한 정보들")
    public List<String> studyWeeks;
}
