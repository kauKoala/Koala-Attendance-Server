package com.example.demo.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data @Getter
@AllArgsConstructor @NoArgsConstructor
@Schema(description = "Lambda 함수에서 백준 크롤링을 하기 위해 받아오는 학생들에 대한 정보 DTO")
public class CrawlingReq {

    private Long studyId;

    private Long studentId;

    private String studentName;

    private String studentBaekjoonName;

    private Long WeekId;
}
