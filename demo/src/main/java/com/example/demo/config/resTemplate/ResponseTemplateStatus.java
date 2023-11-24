package com.example.demo.config.resTemplate;

import lombok.Getter;

@Getter
public enum ResponseTemplateStatus {

    //1000: 요청 성공 및 오류
    SUCCESS(true, "요청 성공", 1000),
    FAIL(false, "요청 실패", 1004),

    //2000: Request 성공 및 오류
    DUPLICATE_STUDENT(false, "일치하는 회원이 이미 존재합니다.", 2000);

    private final boolean isSuccess;
    private final String message;
    private final int code;

    private ResponseTemplateStatus(boolean isSuccess, String message, int code){
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
