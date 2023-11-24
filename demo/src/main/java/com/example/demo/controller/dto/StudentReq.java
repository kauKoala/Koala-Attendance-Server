package com.example.demo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudentReq {

    private String name;
    private String baekjoonName;
    private String tistoryName;
    private Long semesterId;
    private Long studyId;
}
