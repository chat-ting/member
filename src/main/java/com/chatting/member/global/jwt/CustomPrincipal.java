package com.chatting.member.global.jwt;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomPrincipal implements Serializable {
    private Long memberId;
}
