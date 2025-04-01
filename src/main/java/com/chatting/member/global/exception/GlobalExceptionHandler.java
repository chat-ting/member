package com.chatting.member.global.exception;

import com.chatting.member.global.exception.code.CommonErrorCode;
import com.chatting.member.global.exception.code.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    //========== 에러 코드있고, 코드에 메세지 포함되어 있는 경우 시작 ==========
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleCustomException(BusinessException e) {
        ErrorCode errorCode = e.getErrorCode();
        if(e.getEditedMessage() == null) {
            return handleExceptionInternal(errorCode);
        }

        return handleExceptionInternal(errorCode, e.getEditedMessage());
    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllException(Exception e) {
        log.error("UNKNOWN_ERROR");
        e.printStackTrace();
        ErrorCode errorCode = CommonErrorCode.INTERNAL_SERVER_ERROR;
        return handleExceptionInternal(errorCode);
    }
    private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(errorCode));
    }
    private ErrorResponse makeErrorResponse(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .code(errorCode.name())
                .message(errorCode.getMessage())
                .build();
    }
    //========== 에러 코드있고, 코드에 메세지 포함되어이 있는 경우 종료 ==========

    

    //========== 에러 코드 없어서 내부에서 에러 코드 지정해줘야 하는 경우 시작 ==========
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException e) {
        log.warn("handleIllegalArgument", e);
        ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
        return handleExceptionInternal(errorCode, e.getMessage());
    }
    private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode, String message) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(errorCode, message));
    }
    private ErrorResponse makeErrorResponse(ErrorCode errorCode, String message) {
        return ErrorResponse.builder()
                .code(errorCode.name())
                .message(message)
                .build();
    }
    //========== 에러 코드 없어서 내부에서 에러 코드 지정해줘야 하는 경우 종료 ==========



    //========== Validation 관련 에러 시작 ==========
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.warn("handleIllegalArgument", e);
        ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
        return handleExceptionInternal(e, errorCode);
    }
    

    private ResponseEntity<Object> handleExceptionInternal(BindException e, ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(e, errorCode));
    }

    private ErrorResponse makeErrorResponse(BindException e, ErrorCode errorCode) {
        List<ErrorResponse.ValidationError> validationErrorList = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ErrorResponse.ValidationError::of)
                .collect(Collectors.toList());

        return ErrorResponse.builder()
                .code(errorCode.name())
                .message(errorCode.getMessage())
                .errors(validationErrorList)
                .build();
    }
    //========== Validation 관련 에러 종료 ==========
}
