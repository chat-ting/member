package com.chatting.member.service.business_service.kakao;

import com.chatting.member.global.entity.member.Member;
import com.chatting.member.global.entity.member.MemberOAuth;
import com.chatting.member.global.entity.member.MemberOAuthKakao;
import com.chatting.member.global.entity.member.MemberRole;
import com.chatting.member.controller.dto.KakaoUser;
import com.chatting.member.repository.member.MemberRepository;
import com.chatting.member.repository.memberOAuthKakao.MemberOAuthKakaoRepository;
import com.chatting.member.repository.member_OAuth.MemberOAuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class KakaoMemberService {
    @Value("${kakao.host-name}")
    private String KAKAO_HOST_NAME;
    private final MemberOAuthKakaoRepository memberOAuthKakaoRepository;
    private final MemberOAuthRepository memberOAuthRepository;
    private final MemberRepository memberRepository;
    public Optional<MemberOAuthKakao> findByKakaoUserId(String kakaoUserId) {
        return memberOAuthKakaoRepository.findByKakaoId(kakaoUserId);
    }

    @Transactional
    public MemberOAuthKakao joinKakaoMember(KakaoUser kakaoUser) {
        String kakaoUserNickname;
        String kakaoUserEmail;
        try{
            kakaoUserNickname = kakaoUser.getKakaoAccount().getProfile().getNickname();
        }catch (NullPointerException e){
            kakaoUserNickname = "카카오유저";
        }
        try{
            kakaoUserEmail = kakaoUser.getKakaoAccount().getEmail();
        }catch (NullPointerException e){
            //임의의해시값@kakao.user
            kakaoUserEmail = kakaoUser.getId() + "@kakao.temp";
        }


        Member member = Member.builder()
                .email(kakaoUserEmail)
                .nickname(kakaoUserNickname)
                .role(MemberRole.USER.name())
                .build();
        memberRepository.save(member);

        MemberOAuth memberOAuth = MemberOAuth.builder()
                .member(member)
                .host(KAKAO_HOST_NAME)
                .build();
        memberOAuthRepository.save(memberOAuth);

        return memberOAuthKakaoRepository.save(
                MemberOAuthKakao.builder()
                        .kakaoId(kakaoUser.getId())
                        .kakaoNickname(kakaoUserNickname)
                        .kakaoEmail(kakaoUserEmail)
                        .memberOAuth(memberOAuth)
                        .build()
        );
    }
}
