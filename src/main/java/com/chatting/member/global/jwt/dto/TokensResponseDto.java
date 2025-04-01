package com.chatting.member.global.jwt.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class TokensResponseDto {
    private String accessToken;
    private String refreshToken;
}
