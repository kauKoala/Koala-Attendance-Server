package com.example.demo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data @Getter
@AllArgsConstructor
public class CrawlingReq {

    private Long studyId;

    private Long studentId;

    private String studentName;

    private String studentBaekjoonName;

    private Long WeekId;
}
