package com.example.demo.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@Data @NoArgsConstructor
public class StudyRes {

    @Schema(description="id")
    public Long id;
    @Schema(description="스터디 명")
    public String name;

    @Schema(description="시작하는 주차 수")
    public int studyWeeksCount;
}
