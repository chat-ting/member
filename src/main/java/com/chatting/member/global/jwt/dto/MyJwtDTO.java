package com.chatting.member.global.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyJwtDTO {
    private String accessToken;
    private String refreshToken;
}
