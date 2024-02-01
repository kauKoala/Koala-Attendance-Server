package com.example.demo.controller.dto;

import com.example.demo.domain.SemesterType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Data
@AllArgsConstructor @NoArgsConstructor
public class SemesterRes {
    private Long id;
    @Schema(description="해당 년도")
    private String year;
    @Schema(description="1학기, 여름방학, 2학기, 겨울방학으로 type 구분")
    private SemesterType semesterType; //1학기, 여름방학, 2학기, 겨울방학
}
