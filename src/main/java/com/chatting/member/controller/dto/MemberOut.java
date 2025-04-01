package com.chatting.member.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class MemberOut {
    private Long memberId;
    private String memberEmail;
    private String memberNickname;
}
