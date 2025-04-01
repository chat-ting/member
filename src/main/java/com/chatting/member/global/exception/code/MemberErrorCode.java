package com.chatting.member.global.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "member not found"),
    MEMBER_ID_NOT_MATCH(HttpStatus.BAD_REQUEST, "Member id in the request does not match the member id in the header"),
    TELEGRAM_UUID_NOT_FOUND(HttpStatus.BAD_REQUEST,"Register telegram id first" );

    private final HttpStatus httpStatus;
    private final String message;
}
