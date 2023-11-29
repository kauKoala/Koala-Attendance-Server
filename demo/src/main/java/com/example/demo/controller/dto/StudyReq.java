package com.example.demo.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudyReq {
    @Schema(description="스터디 명")
    public String name;
    @Schema(description="스터디에 대한 간단한 소개")
    public String description;
    @Schema(description="총 몇 주까지 진행할 것인지 표시")
    public int totalWeeks;
    @Schema(description="진행하는 학기 mapping")
    public int semesterId;
}
