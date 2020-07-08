package com.example.demo.exception;

public enum  ErrorCode {
    /* redirect to close.jsp */
    FINISHED_VOTE(500, "이미 종료된 투표입니다."),

    /* redirect to return.jsp */
    BAD_CONFIRM(1000, "입력값이 올바르지 않거나 없습니다."),
    PWD_CONFIRM_MISMATCH(1001, "비밀번호 입력값이 일치하지 않습니다."),
    LOGIN_ID_DUPLICATED(1002, "로그인ID가 중복됩니다. 다른 ID를 입력해 주세요."),

    /* error.jsp */
    BAD_PARAMETER(2000, "잘못된 요청입니다.");

    private int errorNumber;
    private String errorMessage;

    private ErrorCode(int errorNumber, String errorMessage) {
        this.errorNumber = errorNumber;
        this.errorMessage = errorMessage;
    }

    public int getNumber(){
        return errorNumber;
    }

    public String getMessage(){
        return errorMessage;
    }
}
