package com.example.demo.controller.dto;

import com.example.demo.domain.SemesterType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SemesterReq {
    private int year;
    private SemesterType semesterType; //1학기, 여름방학, 2학기, 겨울방학
}
