package com.example.demo.controller.dto;

import com.example.demo.domain.Student;
import com.example.demo.domain.Study;
import com.example.demo.domain.Week;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Getter
@AllArgsConstructor
@Schema(description = "Lambda 함수에서 크롤링을 한 후 푼 백준 문제들을 전송하는 DTO")
public class CrawlingRes {

    private Long studyId;

    private Long studentId;

    private Long WeekId;

    private final List<Integer> solvedBaekjoon;

}
