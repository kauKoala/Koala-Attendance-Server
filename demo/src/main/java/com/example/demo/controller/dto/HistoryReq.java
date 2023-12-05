package com.example.demo.controller.dto;

import com.example.demo.domain.Semester;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HistoryReq {

    @Schema(description="기수")
    public String semester;

    @Schema(description = "스터디")
    public String studyName;

}
