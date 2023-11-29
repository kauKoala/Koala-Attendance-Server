package com.example.demo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudyReq {
    public String name;
    public String description;
    public int totalWeeks;
    public int semesterId;
}
