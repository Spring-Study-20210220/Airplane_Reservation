package com.example.demo.Error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Getter
public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE(BAD_REQUEST,"C001","잘못된 입력입니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C002", "허용되지 않은 메소드입니다."),
    UNAUTHORIZED_USER(UNAUTHORIZED,"C003","인가되지 않은 사용자입니다."),
    INVALID_TYPE_VALUE(BAD_REQUEST, "C004", "잘못된 형식입니다."),
    DUPLICATED_ENTITY(BAD_REQUEST,"C005", "중복된 엔티티입니다"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C006", "Server Error"),

    MEMBER_NOT_EXIST(BAD_REQUEST,"U002","존재하지 않는 사용자입니다."),
    ADMIN_NOT_EXIST(BAD_REQUEST,"U003","존재하지 않는 관리자입니다.");

    private final String code;
    private final String message;
    private HttpStatus status;

    ErrorCode(final HttpStatus status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
