package com.chatting.member.service.business_service.member;

import com.chatting.member.global.entity.member.Member;
import com.chatting.member.service.entity_service.member.MemberEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberBusinessService {
    private final MemberEntityService memberEntityService;

    public Member getMemberById(Long memberId) {
        Member member = memberEntityService.getMemberByMemberId(memberId);
        return member;
    }

}
