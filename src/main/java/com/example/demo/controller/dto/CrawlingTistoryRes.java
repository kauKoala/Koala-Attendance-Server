package com.example.demo.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Lambda 함수에서 티스토리 크롤링을 한 후 작성한 학생들을 반환하는 DTO")
public class CrawlingTistoryRes {

    private String writerName;

    private Long WeekId;

    private Long studyId;

}
