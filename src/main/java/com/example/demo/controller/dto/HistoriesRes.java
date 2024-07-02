package com.example.demo.controller.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistoriesRes {

    private String studentName;

    private int weekNum;

    private LocalDate startDate;

    private LocalDate endDate;

    private int solved;

    private int written;
}
