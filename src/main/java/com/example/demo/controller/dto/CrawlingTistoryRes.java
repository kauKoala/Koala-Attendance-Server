package com.example.demo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CrawlingTistoryRes {

    private String writerName;

    private Long WeekId;

    private Long studyId;

}
