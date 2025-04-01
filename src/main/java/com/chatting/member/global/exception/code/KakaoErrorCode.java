package com.chatting.member.global.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@Getter
@RequiredArgsConstructor
public enum KakaoErrorCode implements ErrorCode {
    KAKAO_MEMBER_NOT_EXIST(HttpStatus.NOT_FOUND, "Kakao member not exists")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
