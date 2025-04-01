package com.chatting.member.global.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum KeywordErrorCode implements ErrorCode {
    FORBIDDEN_KEYWORD_ACCESS(HttpStatus.FORBIDDEN, "You do not have permission to access this keyword. The keyword ID does not belong to your account."),
    KEYWORD_NOT_FOUND(HttpStatus.NOT_FOUND, "Keyword does not exists");

    private final HttpStatus httpStatus;
    private final String message;
}
