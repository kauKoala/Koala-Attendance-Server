package com.example.demo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data @Getter
@AllArgsConstructor @NoArgsConstructor
public class CrawlingReq {

    private Long studyId;

    private Long studentId;

    private String studentName;

    private String studentBaekjoonName;

    private Long WeekId;
}
