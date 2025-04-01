package com.chatting.member.global.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode{
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter included"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND,"Resource not exists"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다."),
    NOT_VALID_TOKEN(HttpStatus.UNAUTHORIZED, "해당 토큰은 유효한 토큰이 아닙니다."),
    INVALID_TOKEN_TYPE(HttpStatus.BAD_REQUEST, "유효한 토큰 타입이 아닙니다(ACCESS, REFRESH 확인). 필요한 토큰 타입: {0}"),
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "헤더에 멤버 정보가 없습니다"),
    MEMBER_NOT_POST_OWNER(HttpStatus.BAD_REQUEST, "멤버가 소유한 게시글이 아닙니다."),
    ;
    private final HttpStatus httpStatus;
    private final String message;
}
