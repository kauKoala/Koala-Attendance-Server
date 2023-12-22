package com.example.demo.config.resTemplate;

import lombok.Getter;

@Getter
public enum ResponseTemplateStatus {

    //1000: 요청 성공 및 오류
    SUCCESS(true, "요청 성공", 1000),
    FAIL(false, "요청 실패", 1004),

    //2000: Request 성공 및 오류
    DUPLICATE_STUDENT(false, "일치하는 회원이 이미 존재합니다.", 2000),

    //3000: Study Request
    DUPLICATE_STUDY(false, "일치하는 스터디가 이미 존재합니다.", 3000),
    STUDY_NOT_FOUND(false, "해당 기수에 스터디가 존재하지 않습니다.", 3001),

    //4000: Student Request
    STUDENT_NOT_FOUND(false, "해당 학생이 존재하지 않습니다.", 4000),

    //5000: Semester Request
    DUPLICATE_SEMESTER(false, "일치하는 기수가 이미 존재합니다.", 5000),
    SEMESTER_NOT_FOUND(false, "일치하는 기수가 존재하지 않습니다.", 5001),

    //6000: Week Request
    DUPLICATE_WEEK(false, "해당 학기에 이미 주를 추가하였습니다.", 6000),

    //7000: History Request
    HISTORY_NOT_FOUND(false, "해당 스터디에 히스토리가 존재하지 않습니다.", 7001),

    //8000 StudentStudy Request
    STUDY_NOT_FOUND_FOR_STUDENT(false, "해당 학생은 해당 스터디에 참여하고 있지 않습니다.", 8001),
    DUPLICATE_STUDENT_STUDY(false, "해당 학생은 이미 해당 스터디에 참여하고 있습니다.", 8002);

    private final boolean isSuccess;
    private final String message;
    private final int code;



    private ResponseTemplateStatus(boolean isSuccess, String message, int code){
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
