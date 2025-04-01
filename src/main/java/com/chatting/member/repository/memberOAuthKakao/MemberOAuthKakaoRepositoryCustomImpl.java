package com.chatting.member.repository.memberOAuthKakao;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;



@Repository
@RequiredArgsConstructor
public class MemberOAuthKakaoRepositoryCustomImpl implements MemberOAuthKakaoRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

}
