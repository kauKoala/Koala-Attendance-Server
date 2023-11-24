package com.example.demo.config.resTemplate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class ResponseException extends Exception {
    private ResponseTemplateStatus status;
}