package com.example.demo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class HistoriesRes {

    private String studentName;

    private int weekId;

    private boolean isSolved;

    private boolean isWritten;
}
