package com.example.demo.config.resTemplate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.example.demo.config.resTemplate.ResponseTemplateStatus.SUCCESS;
@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "data"})
public class ResponseTemplate<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;
    private final String message; //메시지 전달
    private final int code; //내부 코드
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    //요청 성공 시
    public ResponseTemplate(T data) {
        this.isSuccess = SUCCESS.isSuccess();
        this.message = SUCCESS.getMessage();
        this.code = SUCCESS.getCode();
        this.data = data;

    }

    //요청 실패시
    public ResponseTemplate(ResponseTemplateStatus status) {
        this.isSuccess = status.isSuccess();
        this.message = status.getMessage();
        this.code = status.getCode();

    }

}