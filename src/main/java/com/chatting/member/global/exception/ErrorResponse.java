package com.chatting.member.global.exception;

import com.chatting.member.global.exception.code.ErrorCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import org.springframework.validation.FieldError;

import java.util.List;

@Builder
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public record ErrorResponse(String code, String message,
                            @JsonInclude(JsonInclude.Include.NON_EMPTY) List<ValidationError> errors) {
    @Builder
    public record ValidationError(String field, String message) {
        public static ValidationError of(final FieldError fieldError) {
            return ValidationError.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
        }
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .code(errorCode.name())
                .message(errorCode.getMessage())
                .build();
    }
}
