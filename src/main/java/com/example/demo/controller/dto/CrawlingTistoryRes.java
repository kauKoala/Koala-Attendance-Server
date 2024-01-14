package com.example.demo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
@Getter
@AllArgsConstructor
public class CrawlingTistoryRes {

    private String writerName;

    private Long WeekId;

    private Long studyId;

}
