package com.chatting.member.service.entity_service.member;

import com.chatting.member.global.entity.member.Member;
import com.chatting.member.global.exception.BusinessException;
import com.chatting.member.global.exception.code.MemberErrorCode;
import com.chatting.member.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional(readOnly = true)
public class MemberEntityService {
    private final MemberRepository memberRepository;

    public Member getMemberByMemberId(long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(MemberErrorCode.MEMBER_NOT_FOUND));
    }



    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}
