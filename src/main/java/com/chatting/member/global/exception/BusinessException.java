package com.chatting.member.global.exception;

import com.chatting.member.global.exception.code.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.text.MessageFormat;

@Getter
@RequiredArgsConstructor
public class BusinessException extends RuntimeException{
    private final ErrorCode errorCode;
    private String editedMessage;
    public BusinessException(ErrorCode errorCode, Object ... objects){
        this.errorCode = errorCode;
        this.editedMessage = MessageFormat.format(errorCode.getMessage(),objects);
    }
}
