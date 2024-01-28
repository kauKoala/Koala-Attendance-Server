package com.example.demo.controller.dto;

import com.example.demo.domain.Student;
import com.example.demo.domain.Study;
import com.example.demo.domain.Week;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Getter
@AllArgsConstructor
public class CrawlingRes {

    private Long studyId;

    private Long studentId;

    private Long WeekId;

    private final List<Integer> solvedBaekjoon;

}
