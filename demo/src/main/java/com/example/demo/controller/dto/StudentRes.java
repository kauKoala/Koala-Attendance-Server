package com.example.demo.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Data
@AllArgsConstructor
public class StudentRes {

    @Schema(description = "id 값")
    public Long id;
    @Schema(description="학생 이름")
    public String name;

}
