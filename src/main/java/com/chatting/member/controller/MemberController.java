package com.chatting.member.controller;

import com.chatting.member.global.util.AuthUtil;
import com.chatting.member.service.business_service.member.MemberBusinessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/api/member")
public class MemberController {
    private final MemberBusinessService memberBusinessService;
    private final AuthUtil authUtil;

}
