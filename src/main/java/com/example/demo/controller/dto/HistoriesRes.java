package com.example.demo.controller.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistoriesRes {

    private String studentName;

    private int weekNum;

    private int solved;

    private int written;
}
