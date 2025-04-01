package com.chatting.member.global.util.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class KakaoTokenReqDTO {
    private String grant_type;
    private String client_id;
    private String redirect_uri;
    private String code;
    private String client_secret;
}
