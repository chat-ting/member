package com.chatting.member.global.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TestErrorCode implements ErrorCode{
    TEST_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"Test error"),
    ;
    private final HttpStatus httpStatus;
    private final String message;
}
