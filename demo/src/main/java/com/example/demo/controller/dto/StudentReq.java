package com.example.demo.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudentReq {

    @Schema(description="학생 이름")
    private String name;
    @Schema(description="학생 백준 아이디")
    private String baekjoonName;
    @Schema(description="학생 티스토리 아이디")
    private String tistoryName;
}
